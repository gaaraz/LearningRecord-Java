# 1.EMQX安装

[安装部署和迁移 | EMQX文档](https://docs.emqx.com/zh/emqx/latest/deploy/install.html)


# 2.报文

## 2.1 常见报文类型

![image.png](/imgResource/image.png)

固定报头固定存在于所有控制报文中，而可变报文和有效载荷是否存在以及它们的内容则取决于具体的报文类型。例如用于维持连接的 PINGREQ 报文就只有一个固定报头，用于传递应用消息的 PUBLISH 报文则完整地包含了这三个部分。

## 2.2 报文格式

在 MQTT 中，无论是什么类型的控制报文，它们都由**固定报头、可变报头和有效载荷**三个部分组成。

固定报头固定存在于所有报文中，而可变报头和有效载荷是否存在以及它们的内容则取决于具体的报文类型。例如用于维持连接的 PINGREQ 报文就只有一个固定报头，用于传递应用消息的 PUBLISH 报文则完整地包含了这三个部分。

### 2.2.1固定报头

| MQTT Control Packet Type(报文类型) | Flags(标识位) | Remaining Length(报文剩余长度) |
| :-------------------------------: | :-----------: | :---------------------------: |
| 4Bit | 4Bit | 1~4 Bytes |

#### 报文类型

占4个bit，是一个无符号整数

常见报文类型：

|名称|值|方向|描述|
|----|---|---|---|
|Reserved|0|Forbidden|保留|
|CONNECT|1|客户端到服务器|连接请求|
|CONNACK|2|服务器到客户端|连接确认|
|PUBLISH|3|双向|发布消息|
|PUBACK|4|双向|发布确认（Qos 1）|
|PUBREC|5|双向|发布已收到（Qos  2 delivery part1）|
|PUBREL|6|双向|发布释放（Qos  2 delivery part2）|
|PUBCOMP|7|双向|发布完成（Qos  2 delivery part3）|
|SUBSCRIBE|8|客户端到服务器|订阅请求|
|SUBACK|9|服务器到客户端|订阅确认|
|UNSUBSCRIBE|10|客户端到服务器|取消订阅请求|
|UNSUBACK|11|服务器到客户端|取消订阅确认|
|PINGREQ|12|客户端到服务器|PING 请求|
|PINGRESP|13|服务器到客户端|PING 响应|
|DISCONNECT|14|双向|断开连接通知|
|AUTH|15|双向|身份验证|

#### 标识位

占4个bit位，不过到MQTT5.0为止，只有PUBLISH报文的这四个比特位被赋予了明确的含义：

1、Bit 3：DUP，表示当前 PUBLISH 报文是否是一个重传的报文。

2、Bit 2,1：QoS，表示当前 PUBLISH 报文使用的服务质量等级。

3、Bit 0：Retain，表示当前 PUBLISH 报文是否是一个保留消息。

其它所有的报文中，这4位都仍是保留的。

#### 剩余长度

剩余长度指示了当前报文剩余部分的字节数，也就是可变报头和有效荷载这两个部分的长度。MQTT报文的总长度=固定报头的长度+剩余长度

### 2.2.2可变报头

可变报头的内容取决于具体的报文类型

举例：

1、CONNECT报文的可变报头顺序包含了协议名、协议级别、连接标识、Keep Alive和属性这五个字段

2、PUBLISH报文的可变报头则按顺序包含了主题名、报文标识符合属性这三个字段

属性这个字段是MQTT5.0引入的。属性字段基本上都是可变报头的最后一部分，由***属性长度***和紧随其后的一组***属性***组成，这里的属性长度指的是后面所有属性的总长度

|可变报头|     |     |
|-|-|-|
|......|Property Length（M）|Property 1 ...... Property N|
||1~4 Bytes|M Bytes|

所有的属性都是可选的，因为它们通常都有一个默认值,如果没有任何属性,那么长度的值就为0。属性通常都是为了某个专门的用途而设计的，不同的报文所支持的属性都是不一样的，具体如下：

|**Identifier**||**Name (usage)**|**Type**|**Packet / Will Properties**|
|-|-|-|-|-|
|**Dec**|**Hex**||||
|1|0x01|Payload Format Indicator|Byte|PUBLISH, Will Properties|
|2|0x02|Message Expiry Interval|Four Byte Integer|PUBLISH, Will Properties|
|3|0x03|Content Type|UTF-8 Encoded String|PUBLISH, Will Properties|
|8|0x08|Response Topic|UTF-8 Encoded String|PUBLISH, Will Properties|
|9|0x09|Correlation Data|Binary Data|PUBLISH, Will Properties|
|11|0x0B|Subscription Identifier|Variable Byte Integer|PUBLISH, SUBSCRIBE|
|17|0x11|Session Expiry Interval|Four Byte Integer|CONNECT, CONNACK, DISCONNECT|
|18|0x12|Assigned Client Identifier|UTF-8 Encoded String|CONNACK|
|19|0x13|Server Keep Alive|Two Byte Integer|CONNACK|
|21|0x15|Authentication Method|UTF-8 Encoded String|CONNECT, CONNACK, AUTH|
|22|0x16|Authentication Data|Binary Data|CONNECT, CONNACK, AUTH|
|23|0x17|Request Problem Information|Byte|CONNECT|
|24|0x18|Will Delay Interval|Four Byte Integer|Will Properties|
|25|0x19|Request Response Information|Byte|CONNECT|
|26|0x1A|Response Information|UTF-8 Encoded String|CONNACK|
|28|0x1C|Server Reference|UTF-8 Encoded String|CONNACK, DISCONNECT|
|31|0x1F|Reason String|UTF-8 Encoded String|CONNACK, PUBACK, PUBREC, PUBREL, PUBCOMP, SUBACK, UNSUBACK, DISCONNECT, AUTH|
|33|0x21|Receive Maximum|Two Byte Integer|CONNECT, CONNACK|
|34|0x22|Topic Alias Maximum|Two Byte Integer|CONNECT, CONNACK|
|35|0x23|Topic Alias|Two Byte Integer|PUBLISH|
|36|0x24|Maximum QoS|Byte|CONNACK|
|37|0x25|Retain Available|Byte|CONNACK|
|38|0x26|User Property|UTF-8 String Pair|CONNECT, CONNACK, PUBLISH, Will Properties, PUBACK, PUBREC, PUBREL, PUBCOMP, SUBSCRIBE, SUBACK, UNSUBSCRIBE, UNSUBACK, DISCONNECT, AUTH|
|39|0x27|Maximum Packet Size|Four Byte Integer|CONNECT, CONNACK|
|40|0x28|Wildcard Subscription Available|Byte|CONNACK|
|41|0x29|Subscription Identifier Available|Byte|CONNACK|
|42|0x2A|Shared Subscription Available|Byte|CONNACK|

### 2.2.3有效载荷

有效载荷是用于实现对应报文的核心功能。

举例：

1、在 PUBLISH 报文中，Payload 用于承载具体的应用消息内容，这也是 PUBLISH 报文最核心的功能。

2、在 SUBSCRIBE 报文中，Payload 包含了想要订阅的主题以及对应的订阅选项，这也是 SUBSCRIBE 报文最主要的工作。

## 2.3 报文验证

![image1.png](/imgResource/image1.png)

分析：

Connect报文：

    固定报头：

    报文类型为1且占4bit，标识位保留都是0，所以值为0001 0000 → 0x10

    剩余长度           24(Bytes)

    可变报头：

    协议名                MQTT（6Bytes）

    协议级别             v3.1.1  (1Bytes)

    连接标识             0x02    (1Bytes)

    Keep Alive          60       (2Bytes)

    属性

    有效载荷

    Client ID              14Bytes

对于各种类型消息的详解参照:
[MQTT Version 5.0](https://docs.oasis-open.org/mqtt/mqtt/v5.0/os/mqtt-v5.0-os.html#_Toc3901032)

# 3.Qos

## 3.1 简介

使用MQTT协议的设备大部分是运行在网络受限的环境下，而只依靠底层TCP传输协议，并不能完全保证消息的可靠到达。

MQTT提供了Qos机制，其核心是设计了多种消息交互机制来提供不同的服务质量，来满足各种场景下对消息可靠性的要求。

MQTT定义了三个Qos等级，分别为：

1. Qos 0，最多交付一次 =====> 可能丢失消息

2. Qos 1，至少交付一次 =====> 可以保证收到消息，但消息可能重复

3. Qos 2，只交付一次 =====> 可以保证消息既不丢失也不重复

Qos等级是由发布者在PUBLIS报文中指定的，大部分情况下Broker向订阅者转发消息时都会维持原始的Qos不变。不过也有一些例外的情况，根据订阅者的订阅要求，消息的Qos等级可能会在转发的时候发生降级。

例如，订阅者在订阅时要求Broker可以向其转发的消息最大Qos等级为Qos1，那么后续所有Qos2消息都会降级至Qos1转发给此订阅者，而Qos0和Qos1的消息则会保持原始的Qos等级转发。

## 3.2 Qos 0

Qos 0消息即发即弃，不需要等待确认，不需要存储和重传，因此对于接收方来说，永远都不需要担心收到重复的消息。因此一旦出现连接关闭、重置的情况，就有可能丢失当前处于网络链路或操作系统底层缓冲区中的消息。

## 3.3 Qos 1

为了保证消息到达，Qos 1加入了应答与重传机制，发送方只有在收到接收方的PUBACK报文以后，才能认为消息投递成功，在此之前，发送方需要存储该PUBLISH报文以便下次重传。

Qos 1需要在PUBLISH报文中设置Packet ID，而作为响应的PUBACK报文，则会使用与PUBLISH报文相同的Packet ID，以便发送方收到后删除正确PUBLISH报文缓存。

对于发送方来说，没收到PUBACK报文分为一下两种情况：

1. PUBLISH未到达接收方

2. PUBLISH已经到达接收方，接收方的PUBACK报文还未到达发送方

在第一种情况下，发送方虽然重传了PUBLISH报文，但是对于接收方来说，实际上任然仅收到了一次消息。

早第二种情况下，在发送方重传时，接收方已经收到过了这个PUBLISH报文，这就导致接收方收到重复的消息。

重传PUBLISH报文的时候，PUBLIASH中的DUP标志会被置为1，用以表示这是一个重传的报文。

## 3.3 Qos2

QoS 2 解决了 QoS 0、1 消息可能丢失或者重复的问题，但相应地，它也带来了最复杂的交互流程和最高的开销。每一次的Qos 2消息投递，都要求发送方与接收方进行至少两次请求/响应流程。

流程：

1. 发送方存储并发送 QoS 为 2 的 PUBLISH 报文以启动一次 QoS 2 消息的传输，然后等待接收方回复 PUBREC 报文。这一部分与 QoS 1 基本一致，只是响应报文从 PUBACK 变成了 PUBREC。

2. 当发送方收到PUBREC报文，即可确认接收方已经收到了PUBLISH报文，发送方将不再需要重传这个报文，并且也不能再重传这个报文。所以此时发送方可以删除本地存储的PUBLISH报文，然后发动一个PUBREL报文，通知接收方自己准备将本次使用的Packet ID标记为可用了。与PUBLISH报文一样，我们需要确保PUBREL报文达到接收方，所以也需要一个响应报文，并且这个PUBLISH报文需要被存储下来以便后续重传。

3. 当接收方收到PUBREL报文，也可以确认在这一次的传输流程中不会再有重传的PUBLISH报文到达，因此回复PUBCOMP报文表示自己也准备好将当前的Packet ID用于新的消息了。

4. 当发送方收到PUBCOMP报文，这一次的Qos2消息传输就算正式完成了。在这之后，发送方可以再次使用当前的Packet ID发送新的消息，而接收方再次收到使用这个Packet ID的PUBLISH报文时，也会将它视为一个全新的消息。

## 3.4 不同Qos的适用场景

Qos 0 缺点是可能会丢失消息，消息丢失的频率依赖于你所处的网络环境，并且可能使你错过断开连接期间的消息，不过优点是投递的效率较高。所以我们通常选择使用Qos 0 传输一些高频且不那么重要的数据，比如传感器数据，周期性更新，即使遗漏几个周期的数据也可以接受。

Qos 1 可以保证消息到达，所以合适传输一些较为重要的数据，比如下达关键指令、更新重要的有实时性要求的状态等。但因为Qos 1 还可能会导致消息重复，所以当我们选择使用Qos 1时，还需要能够处理消息的重复，或者能够允许消息的重复。

Qos 2 既可以保证消息到达，也可以保证消息不会重复，但传输成本最高。如果我们不愿意自行实现去重方案，并且能够接受Qos 2 带来的额外开销，那么Qos 2 将是一个合适的选择。通常我们会在金融、航空等行业场景下会更多的见到Qos 2的使用。

# 4.主题

MQTT主题本质上是一个UTF-8编码的字符串，是MQTT协议进行消息路由的基础。MQTT主题类似URL路径，使用斜杠/进行分层：

```Shell
chat/room/1
test/10/temperature
test/+/temperature
test/#
```

为了避免歧义且易于理解,通常不建议主题以/开头或结尾，例如/chat或chat/。

MQTT主题不需要提前创建。MQTT客户端在订阅或发布时即自动的创建了主题，开发者无需再关心主题的创建，并且也不需要手动删除主题。

## 4.1 主题通配符

MQTT主题通配符包含单层通配符+及多层通配符#，主要用于客户端一次订阅多个主题。

### 4.1.1 单层通配符

加号("+")是用于单个主题层级匹配的通配符。在使用单层通配符时，单层通配符必须占据整个层级，例如

```Shell
+                     有效
test/+                有效
test/+/temperature    有效
test+                 无效(没有占据整个层级)
```

    如果客户端订阅了主题test/+/temperature,将会收到以下主题的消息:

```Shell
test/1/temperature
test/2/temperature
...
test/n/temperature
```

            

### 4.1.2 多层通配符

井字符号（"**#**"）是用于匹配主题中任意层级的通配符。多层通配符表示它的父级和任意数量的子层级，在使用多层通配符时，它必须占据整个层级并且必须是主题的最后一个字符，例如

```Shell
#                   有效，匹配所有主题
test/#              有效
test/bedroom#       无效（没有占据整个层级）
test/#/temperature  无效（不是主题最后一个字符）
```

如果客户端订阅主题 test/#，它将会收到以下主题的消息：

```Shell
test
test/temperature
test/1/temperature
```

## 4.2 系统主题

以 $SYS/ 开头的主题为系统主题，系统主题主要用于获取MQTT服务器自身运行状态、消息统计、客户端上下线事件等数据。目前，MQTT协议暂未明确规定 $SYS/ 主题标准，但大多数MQTT服务器都遵循该标准建议。

例如，EMQX服务器系统主题，可参考

[系统主题 | EMQX 5.0 文档](https://docs.emqx.com/zh/emqx/v5.0/observability/mqtt-system-topics.html)

# 5. 会话

MQTT客户端和MQTT服务器之间的连接被称为会话。每个MQTT客户端都可以启动一个或多个会话，通过会话可以实现客户端和服务器之间的消息传递。

## 5.1 常见配置参数

### 5.1.1 Clean Start

Clean Start作用：用于指示客户端在和服务器建立连接的时候应该尝试恢复之前的会话还是直接创建全新的会话

常见取值以及含义：

0：服务端存在一个关联此客户端标识符（Client ID）的会话，服务端**必须**基于此会话的状态恢复与客户端的通信（之前的订阅信息会再次绑定，并且会接收到客户端断开时，发布者所发布的消息）。如果不存在任何关联此客户端标识符的会话，服务端**必须**创建一个新的会话。

1：客户端和服务端**必须**丢弃任何已存在的会话，并开始一个新的会话。

### 5.1.2 Session Expiry Interval

Session Expiry Interval 决定了会话状态数据在服务端的存储时长。

常见取值：

- 没有指定此属性或者设置为 0，表示会话将在网络连接断开时立即结束。

- 设置为一个大于 0 的值，则表示会话将在网络连接断开的多少秒之后过期。

- 设置为 0xFFFFFFFF，即 Session Expiry Interval 属性能够设置的最大值时，表示会话数据永不过期。

服务端使用 `Client ID` 来唯一地标识每个会话，如果客户端想要在连接时复用之前的会话，那么必须使用与此前一致的 Client ID。

# 6. 消息详解

## 6.1 保留消息

普通消息：普通消息在发送之前其所对应的主题如果不存在订阅者，普通消息MQTT服务器会直接将其丢弃。

保留消息：保留消息可以保留在MQTT服务器中。任何新的订阅者订阅与该保留消息中的主题匹配的主题时，都会立即接收到该消息，即使这个消息是在它们订阅主题之前发布的。

使用场景：

1. 智能家居设备的状态只有在变更时才会上报，但是控制端需要在上线后就能获取到设备的状态；

2. 传感器上报数据的间隔太长，但是订阅者需要在订阅后立即获取到最后的数据；

3. 传感器的版本号、序列号等不会经常变更的属性，可在上线后发布一条保留消息告知后续的所有订阅者；

注：

- MQTT服务器会为每个主题存储最新一条保留消息。

- 在保留消息发布前订阅主题，将不会收到保留消息。需要待保留消息发布后，重新订阅该主题，才会收到保留消息。

- 保留消息的存储方式：内存存储（默认存储类型）、磁盘存储

- 保留消息虽然存储在服务端中，但它并不属于会话的一部分。也就是说，即便发布这个保留消息的会话已结束，保留消息也不会被删除。

- 客户端往某个主题发送一个 Payload 为空的保留消息，服务端就会删除这个主题下的保留消息

- MQTT 5.0 新增了消息过期间隔属性，发布时可使用该属性设置消息的过期时间，将会在过期时间后自动被删除。

        

## 6.2 消息过期间隔

MQTT 可以通过Session Expiry Interval为离线客户端缓存尚未发送的消息，然后在客户端恢复连接时发送。但如果客户端离线时间较长，可能有一些寿命较短的消息已经没有必要发送给客户端了，继续发送这些过期的消息，只会浪费网络带宽和客户端资源。

消息过期间隔是 MQTT 5.0 引入的一个新特性，它允许发布端为有时效性的消息设置一个过期间隔，如果该消息在服务端中停留超过了这个指定的间隔，那么服务端将不会再将它分发给订阅端。默认情况下，消息不会包含消息过期间隔，这表示该消息永远不会过期。

注：如果客户端在发布消息时设置了过期间隔，那么服务端在转发这个消息时也会包含过期间隔，但过期间隔的值会被更新为服务端接收到的值减去该消息在服务端停留的时间。这可以避免消息的时效性在传递的过程中丢失，特别是在桥接到另一个MQTT服务器的时候。

## 6.3 遗嘱消息

### 6.3.1 简介

在MQTT中，客户端可以在连接时在服务端中注册一个遗嘱消息，与普通消息类似，我们可以设置遗嘱消息的主题、有效载荷等等。当该客户端意外断开连接，服务端就会向其他订阅了该主题的客户端发送此遗嘱消息。这些接收者也因此可以及时的采取行动，例如向用户发送通知、切换备用设备等等。

作用：借助于遗嘱消息可以感知到客户端是意外断开

### 6.3.2 原理

遗嘱消息在客户端发起连接时指定，它和Client ID、Clean Start这些字段一起包含在客户端发送的CONNECT报文中。

与普通消息一样，我们可以为遗嘱消息设置主题（Will Topic）、保留消息标识位（Will Retain）、属性（Will Properties）、Qos（Will Qos）和有效载荷（Will Payload）。

这些字段的用法与它们在普通消息中时完全相同，遗嘱消息只是多了一个专属属性：**Will Delay Interval**。

**Will Delay Interval：这个属性决定了服务端将在网络连接关闭后延迟多久发布遗嘱消息，并以秒为单位。**

默认情况下，服务端总是在网络连接意外关闭时立即发布遗嘱消息。但是很多时候，网络连接的中断是短暂的，所以客户端往往能够重新连接并继续之前的会话。这导致遗嘱消息可能被频繁地且无意义地发送。

如果没有指定 Will Delay Interval 或者将其设置为 0，服务端将仍然在网络连接关闭时立即发布遗嘱消息。

但如果将 Will Delay Interval 设置为一个大于 0 的值，并且客户端能够在 Will Delay Interval 到期前恢复连接，那么该遗嘱消息将不会被发布。

#### 遗嘱消息与会话

遗嘱消息是会话状态的一部分，当会话结束，遗嘱消息也无法继续单独存在。但是在遗嘱消息延迟发布期间，会话可能过期，也可能因为客户端在新的连接中设置Clean Start 为 1 所以服务端需要丢弃之前的会话。为了避免丢失遗嘱，此时服务端必须发布该遗嘱消息，即便 Will Delay Interval 还没有到期。所以服务端最终何时发布遗嘱消息，取决于 Will Delay Interval 到期和会话结束这两种情况谁先发生。

## 6.4 延迟发布

简介：MQTT服务端收到发布者发布的消息以后，延迟一段时间以后再把消息转发给订阅者。

使用场景：

1. 农业智能化管理：在智能农业中，可能需要在特定时间启动灌溉系统或调节温室环境。通过MQTT延迟发布，可以预先设定好指令发布时间，如在清晨或傍晚自动发送开启灌溉的信号，确保水资源的有效利用且不对作物生长周期造成干扰。

2. 能源管理与自动控制：智能家居或智能建筑中的照明、供暖、通风系统可以根据居民生活习惯或节能策略，利用延迟发布在预设时间自动调整，如在居民到家前半小时开启空调或在离开家后一定时间关闭所有非必要电器，以达到节能减排的目的。

3. 公共设施维护：城市中的公共照明、广告牌等设施可能需要在特定时间统一开关，以节省能源或遵守当地法规。通过MQTT延迟发布功能，可以安排在夜间自动发送开关指令，无需人工干预，简化运维流程。

4. 医疗健康监护：在远程医疗监护中，设备可能需要在一天中的特定时间收集患者数据或发送提醒，如定时提醒患者服药或在固定时间收集心率、血压等生理参数，以优化患者护理计划。

延迟发布主题格式：

    $delayed/{DelayInterval}/{TopicName}

    $delayed：使用$delayed作为主题前缀的消息都将被视为需要延迟发布的消息

    DelayInterval：延迟发布的时间间隔，单位为秒，允许的最大时间间隔是4294967秒。如果{DelayInterval}无法被解析为一个整型数字，EMQX将丢弃该消息，客户端不会接收到任何消息。

    TopicName：MQTT消息的主题名称

举例：

```Shell
$delayed/15/x/y：15 秒后将 MQTT 消息发布到主题 x/y。
$delayed/60/a/b：1 分钟后将 MQTT 消息发布到 a/b。
```

## 6.5 用户属性

简介：

1. MQTT5.0版本引入的一个新特性。

2. 它允许在Publish、Subscribe、Connect、Disconnect等报文中携带附加信息。

3. 类似于http协议的请求头。

应用场景：

1. 日志记录：在发布（PUBLISH）和订阅（SUBSCRIBE）报文中加入用户属性，可以帮助记录操作者信息、操作时间、原因说明等，便于后续的审计跟踪和问题排查。

2. 消息分类与标记：用户属性可以用来给消息添加标签或分类信息，如消息类型等，使得接收方能根据这些属性对消息进行过滤、排序或特殊处理。

# 7.EMQX Dashboard

    访问地址： http://ip:18083

    首次登录访问账号：admin/public

重置密码:

```Shell
./bin/emqx ctl admins passwd <Username> <Password>
```

## 各种详细操作可参照官网文档

[EMQX Dashboard | EMQX文档](https://docs.emqx.com/zh/emqx/latest/dashboard/introduction.html)


