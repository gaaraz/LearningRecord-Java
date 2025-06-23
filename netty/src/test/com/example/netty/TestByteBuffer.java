package com.example.netty;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.ObjectUtils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.netty.ByteBufferUtil.debugAll;

/**
 * @description:
 * @author: zzy
 * @createDate: 2023/10/24
 */
@Slf4j
public class TestByteBuffer {
    @Test
    public void testByteBuffer(){
        log.info("test");
        // 1.输入输出流,2.RandomAccessFile
        try(FileChannel channel = new FileInputStream("data.txt").getChannel()) {
            // 准备缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(10);
            while (true){
                // 从channel读取数据,向buffer写入
                int len = channel.read(buffer);
                log.debug("获取到的字节长度{}",len);
                if (len == -1){ // 没有内容了
                    break;
                }
                buffer.flip();  // 切换至读模式
                while (buffer.hasRemaining()){  // 是否还有剩余未读数据
                    byte b = buffer.get();
                    System.out.println((char) b);
                }
                buffer.clear();     // 切换为写模式
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @Test
    public void testByteBufferReadWrite(){
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put((byte) 0x61);
        debugAll(buffer);
        buffer.put(new byte[]{0x62, 0x63, 0x64});
        debugAll(buffer);
//        System.out.println(buffer.get());
        buffer.flip();
        System.out.println(buffer.get());
        debugAll(buffer);
        buffer.compact();
        debugAll(buffer);
        buffer.put(new byte[]{0x65, 0x66});
        debugAll(buffer);
    }

    @Test
    public void testByteBufferAllocate(){
        System.out.println(ByteBuffer.allocate(16).getClass());
        System.out.println(ByteBuffer.allocateDirect(16).getClass());
        /**
         * class java.nio.HeapByteBuffer    java堆内存,,读写效率低,受垃圾回收 GC的影响
         *                                  适用于数据量较小、频繁创建和释放的场景
         * class java.nio.DirectByteBuffer  直接内存，读写效率高(少一次拷贝)，不会受 GC的影响
         *                                  使用完后 需要彻底的释放，以免内存泄露
         *                                  适用于需要频繁进行I/O操作的场景，对于大量的数据存取操作，可以提高性能。
         * HeapByteBuffer 类具有以下属性和方法：
         *
         * position（位置）：position 属性表示当前缓冲区的位置，即下一个要读取或写入的字节的索引。初始时，position 的值为 0。
         *                 通过调用 position() 方法可以获取当前位置的值，
         *                 通过调用 position(int newPosition) 方法可以设置新的位置值。
         *                 每一次 get() or get(byte[])时，position++; (注意不是get(i),get(i)不会让position++)
         *
         * limit（限制）   ：limit 属性表示缓冲区中可读取或写入的字节的索引的上限。初始时，limit 的值等于缓冲区的容量。
         *                 通过调用 limit() 方法可以获取当前限制的值，
         *                 通过调用 limit(int newLimit) 方法可以设置新的限制值。
         *
         * capacity（容量）：capacity 属性表示缓冲区的容量，即缓冲区可以存储的最大字节数。
         *                 通过调用 capacity() 方法可以获取当前容量的值。
         * position 的值必 <= limit 的值，
         * 而 limit 的值必 <= capacity 的值
         */
    }

    @Test
    public void testByteBufferRead(){
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put(new byte[]{'a','b','c','d'});
        debugAll(buffer);
        // flip    切换读模式  切换至 读模式 [ position指针指向开头， limit指向写入的最后位置 (内存长度)
        buffer.flip();
        debugAll(buffer);

        // rewind从头开始读,下标 position 设 0 , 从头开始，而不会改limit值
        buffer.get(new byte[3]);
        debugAll(buffer);
        buffer.compact();
        debugAll(buffer);
//        buffer.rewind();
//        System.out.println((char) buffer.get());

        // mark & reset
        // mark做一个标记,记录position位置,reset是将position重置到mark的位置
//        System.out.println((char) buffer.get());
//        System.out.println((char) buffer.get());
//        buffer.mark(); //标记索引2位置
//        System.out.println((char) buffer.get());
//        System.out.println((char) buffer.get());
//        buffer.reset(); //将position重置到索引2
//        System.out.println((char) buffer.get());
//        System.out.println((char) buffer.get());

        // get(i)不会改变索引位置
//        System.out.println((char) buffer.get(2));
//        debugAll(buffer);
    }

    @Test
    public void testByteBufferString(){
        // 字符串转为ByteBuffer
        ByteBuffer buffer = ByteBuffer.allocate(16);
        buffer.put("hello".getBytes());
        debugAll(buffer);

        // Charset
        ByteBuffer buffer1 = StandardCharsets.UTF_8.encode("hello");
        debugAll(buffer1);

        //wrap
        ByteBuffer buffer2 = ByteBuffer.wrap("hello".getBytes());
        debugAll(buffer2);

        // 转字符串
        String str1 = StandardCharsets.UTF_8.decode(buffer1).toString();
        System.out.println(str1);

        buffer.flip();
        String str2 = StandardCharsets.UTF_8.decode(buffer).toString();
        System.out.println(str2);
    }

    /**
     * 分散读取
     */
    @Test
    public void testScatteringReads(){
        try(FileChannel channel = new RandomAccessFile("ScatteringReads.txt", "r").getChannel()){
            ByteBuffer buffer1 = ByteBuffer.allocate(3);
            ByteBuffer buffer2 = ByteBuffer.allocate(3);
            ByteBuffer buffer3 = ByteBuffer.allocate(5);
            channel.read(new ByteBuffer[]{buffer1, buffer2, buffer3});
            debugAll(buffer1);
            debugAll(buffer2);
            debugAll(buffer3);
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 集中写入
     */
    @Test
    public void testGatheringWrites(){
        try(FileChannel channel = new RandomAccessFile("ScatteringReads.txt", "rw").getChannel()){
            ByteBuffer buffer4 = StandardCharsets.UTF_8.encode("four");
            ByteBuffer buffer5 = StandardCharsets.UTF_8.encode("five");
            channel.position(11);
            debugAll(buffer4);
            debugAll(buffer5);
            channel.write(new ByteBuffer[]{buffer4, buffer5});
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 黏包半包分析
     */
    @Test
    public void testByteBufferExam(){
        ByteBuffer source = ByteBuffer.allocate(32);

        source.put("Hello,world\nI'm zhangsan\nHo".getBytes());
        split(source);

        source.put("w are you?\nhaha!\n".getBytes());
        split(source);
    }

    private static void split(ByteBuffer source){
        source.flip();
        for (int i = 0; i < source.limit(); i++){
            if (source.get(i) == '\n'){
                int length = i + 1 - source.position();
                ByteBuffer target = ByteBuffer.allocate(length);
                for (int j = 0; j<length; j++){
                    target.put(source.get());
                }
                debugAll(target);
            }
        }
        source.compact();
    }

    @Test
    public void testFileChannelTransferTo(){
        FileChannel from = null;
        FileChannel to = null;
        try {
            from = new FileInputStream("data.txt").getChannel();
            to = new FileOutputStream("to.txt").getChannel();
            from.transferTo(0, from.size(), to);
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (from != null){
                try {
                    from.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (to != null){
                try {
                    to.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testBigFileChannelTransferTo(){
        FileChannel from = null;
        FileChannel to = null;
        try {
            from = new FileInputStream("data.txt").getChannel();
            to = new FileOutputStream("to.txt").getChannel();
            long size = from.size();
            for (long left = size; left > 0;){
                System.out.println("position:" + (size - left) + "left:" + left);
                long transfer = from.transferTo((size - left), left, to);
                left = left - transfer;
            }
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (from != null){
                try {
                    from.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (to != null){
                try {
                    to.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testFilesWalkFileTree() throws IOException {
        AtomicInteger dirCount = new AtomicInteger();
        AtomicInteger fileCount = new AtomicInteger();
        Files.walkFileTree(Paths.get("/Users/Gaara/Desktop"), new SimpleFileVisitor<Path>(){
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println(dir);
                dirCount.incrementAndGet();
                return super.preVisitDirectory(dir, attrs);
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println(file);
                fileCount.incrementAndGet();
                return super.visitFile(file, attrs);
            }
        });
        System.out.println(dirCount);
        System.out.println(fileCount);
    }

    @Test
    public void test(){
//        LocalDateTime now = LocalDateTime.now();
//        // 计算出采集日期当月最后一天日期
//        LocalDate lastDateOfMonth = now.with(TemporalAdjusters.lastDayOfMonth()).toLocalDate();
//        // 判断是否当月最后一天
//        System.out.println(lastDateOfMonth.isEqual(now.toLocalDate()));

        Integer year = 2023;
        Integer month = 2;
        month -=1;
        String start = null;
        String end = null;
        if (Objects.nonNull(year)){
            Calendar startCal = Calendar.getInstance();
            Calendar endCal = Calendar.getInstance();
            startCal.set(Calendar.YEAR, year);
            endCal.set(Calendar.YEAR, year);
            startCal.set(Calendar.DAY_OF_MONTH, 1);
            endCal.set(Calendar.DAY_OF_MONTH, 1);
            if (!ObjectUtils.isEmpty(month)){
                startCal.set(Calendar.MONTH, month);
                endCal.set(Calendar.MONTH, month);
            } else {
                startCal.set(Calendar.MONTH, Calendar.JANUARY);
                endCal.set(Calendar.MONTH, Calendar.DECEMBER);
            }
            startCal.set(Calendar.DAY_OF_MONTH, 1);
            startCal.set(Calendar.HOUR_OF_DAY, 0);
            startCal.set(Calendar.MINUTE, 0);
            startCal.set(Calendar.SECOND, 0);
            System.out.println(startCal.getTime().getTime());
            LocalDateTime startTime = LocalDateTime.ofInstant(startCal.toInstant(), startCal.getTimeZone().toZoneId());
            System.out.println(endCal.getActualMaximum(Calendar.DAY_OF_MONTH));
            endCal.set(Calendar.DAY_OF_MONTH, endCal.getActualMaximum(Calendar.DAY_OF_MONTH));
            endCal.set(Calendar.HOUR_OF_DAY, 23);
            endCal.set(Calendar.MINUTE, 59);
            endCal.set(Calendar.SECOND, 59);
            LocalDateTime endTime = LocalDateTime.ofInstant(endCal.toInstant(), endCal.getTimeZone().toZoneId());
            start = startTime.toString();
            end = endTime.toString();
        }
        System.out.println(start);
        System.out.println(end);
//        String videoUrl = "ezopen://open.ys7.com/AB2353766/1.rec";
//        String[] split = videoUrl.split("ezopen://open.ys7.com/");
//        String deviceSerial = split[1].split("/")[0];
//        String channelNo = split[1].split("/")[1].split("\\.")[0];
//        System.out.println(deviceSerial);
//        System.out.println(channelNo);
//        String suffix = videoUrl.substring(videoUrl.lastIndexOf("."));
//        System.out.println(suffix);

//        JSONObject smsContentJson = new JSONObject();
//        smsContentJson.put("projectName", "名字1");
//        smsContentJson.put("number", 111);
//        String templateContent = "${projectName}，本周已提交${number}条项目周查单，本周需要提交1条项目周查单，已完成请忽略，未完成请及时巡检。【常安智慧工地管家】";
//        String r = "\\$\\{([^\\}]+)\\}";
//        Pattern pattern = Pattern.compile(r);
//        Matcher matcher = pattern.matcher(templateContent);
//        while (matcher.find()){
//            String group = matcher.group();
//            Object o = smsContentJson.get(group.replaceAll(r, "$1"));if (o != null){
//                templateContent = templateContent.replace(group, String.valueOf(o));
//            } else {
//                templateContent = templateContent.replace(group, "");
//            }
//        }
//        System.out.println(templateContent);
    }

    public static void main(String[] args) {
        System.out.println("1111");
    }
}
