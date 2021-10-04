# Spring-boot-learn笔记

官网文档：https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features

## Logback日志

### 理论知识

#### slf4j

slf4j：一个针对各类Java日志框架的统一facade抽象。

java常见日志框架：java.util.logging, log4j, logback, commons-logging

logback是log4j的作者开发的新一代日志框架，目前应用最广泛。SpringBoot默认使用logback，默认INFO级别

日志加载顺序：logback.xml -> application.properties -> logback-spring.xml

#### 日志级别

log级别：FATAL, ERROR, WARN, INFO, DEBUG, TRACE, ALL, OFF

优先级：ALL < TRACE < DEBUG < INFO < WARN < ERROR < FATAL < OFF

日志系统会输出大于等于当前配置级别的日志。如，配置级别为WARN时，系统会输出 WARN,ERROR , FATAL类型日志。

***注：在logback中没有FATAL等级，该等级被映射到了ERROR。***

Log4j建议只使用四个级别，优先级从高到低分别是ERROR、WARN、INFO、DEBUG。

一下对几个的特别的日志级别进行解释：

##### OFF

该日志级别会关闭系统的日志输出，任何级别的日志信息都不会输出，包括重要的ERROR和FATAL日志，（ps: 如果开启该日志等级，可能线上的服务挂了看不到，反正什么日志都没有）。

##### FALTAL

输出灾难性错误，或者成为致命错误，例如：使用FATAL输出无法连接数据的错误。出现这个问题时，就可以做好数据保存和日志输出，通知oncall，然后停止程序了。而且没看到slf4j框架怎么输出这个等级的日志。

***在开发过程中使用选择合适的日志，能够有效提高程序调试和线上问题的排查***

### 代码整合

#### 依赖

（1）直接引入：

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-logging</artifactId>
  <version>2.1.11.RELEASE</version>
  <scope>compile</scope>
</dependency>
```

（2）间接应用：

引入spring-boot-starter，会自动引入spring-boot-starter-logging

由于Spring boot默认使用logback作为日志系统的实现，所以在spring-boot-starter中已经导入了logback，因此使用logback时，无需手动添加依赖。

如果需要在项目中使用log4j2需要重新配置依赖，后续会进行介绍。

#### 配置logback

使用日志系统时，主要是需要合理的配置日志系统，在使用过程中只需要简单的使用@Slf4j或者生成logging进行打印。介绍两种配置方式，系统任选一种，切勿两种配置同时使用。

两种配置的优先级：logback.xml -> application.properties -> logback-spring.xml、

logback框架下：

建议使用logback-spring.xml，也可以在application中通过logging.config=classpath:xxx.xml来指定配置文件。

##### Spring Boot配置文件配置logback

```yaml
logging:
  level:
    root: "warn"
    com.findstar.springbootlearn: "trace"
  file:
    name: "./log/spring-boot-learn.log"
```

参考配置链接：https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.logging

##### xml配置logback

```xml
<?xml version="1.0" encoding="utf-8"?>
<!--根结点：configuration，包含以下三个属性-->
<!--scan：是否启动配置文件扫描，为true时，如果配置文件发生变化，会被重新加载，默认为true-->
<!--scanPeriod：设置配置文件扫描周期，默认单位为ms(可以显示设置)，默认扫描周期为1分钟-->
<!--debug：是否答应logback内部的日志信息，默认为false-->
<configuration scan="true" scanPeriod="60 seconds" debug="false">

    <!-- property-->
    <!-- 用于定义变量，有name和value两个属性，可以用过${}来使用变量-->
    <!-- 设置项目名称-->
    <property name="project-name" value="spring-boot-learn"/>

    <!-- 用于设置上下文名称，默认为default，一旦设置不能修改，使用-->
    <contextName>${project-name}</contextName>

    <!--timestamp-->
    <!--获取时间戳字符串-->
    <!--key： 标识名字，通过${}引用-->
    <!--datePattern：设置将当前时间（解析配置文件的时间）转换为字符串的模式，遵循java.txt.SimpleDateFormat的格式。-->
    <timestamp key="myTimestamp" datePattern="yyyy-MM-dd"/>

    <!-- 配置模版-->
    <property name="pattern" value="%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg %n"/>
    <property name="pattern-color"
              value="%yellow(%d{yyyy-MM-dd HH:mm:ss.SSS}) [%thread] %highlight(%-5level) %green(%logger{50}) - %highlight(%msg) %n"/>
    <property name="LOG_HOME" value="log"/>

    <!--appender-->
    <!--配置日志输出组件-->
    <!--name:指定名字，引用方式如：<appender-ref ref="STDOUT" /> -->
    <!--class:指定appender的全限定名称，-->
    <!--    控制台输出：ch.qos.logback.core.ConsoleAppender-->
    <!--    文件输出：ch.qos.logback.core.FileAppender-->
    <!--    滚动记录文件：ch.qos.logback.core.rolling.RollingFileAppender-->
    <!--    其他类型...-->


    <!--ConsoleAppender 把日志输出到控制台，有以下子节点：-->
    <!--<encoder>：对日志进行格式化。（-->
    <!--<target>：字符串System.out(默认)或者System.err-->
    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <pattern>${pattern}</pattern>
        </encoder>
    </appender>
    <!-- 控制台输出-带颜色 -->
    <appender name="CONSOLE-WITH-COLOR" class="ch.qos.logback.core.ConsoleAppender">
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <pattern>${pattern-color}</pattern>
        </encoder>
    </appender>

    <!--FileAppender：日志输出到文件-->
    <!--<file>：被写入的文件名，可以是相对目录，也可以是绝对目录，如果上级目录不存在会自动创建，没有默认值。-->
    <!--<append>：如果是true，日志被追加到文件结尾；如果是false，清空现存文件。默认true。-->
    <!--<encoder>：设置日志输出格式。-->
    <!--<prudent>：如果是true，日志会被安全的写入文件，即使其他的FileAppender也在向此文件做写入操作，效率低，默认false。-->
    <appender name="FILE" class="ch.qos.logback.core.FileAppender">
        <file>${LOG_HOME}/${project-name}.${myTimestamp}.log</file>
        <append>true</append>
        <encoder>
            <pattern>${pattern}</pattern>
        </encoder>
        <prudent>true</prudent>
    </appender>

    <!--RollingFileAppender：滚动记录文件，先将日志记录到指定文件，当符合某个条件时，将日志记录到其他文件。有以下子节点：-->
    <!--<file>：被写入的文件名，可以是相对目录，也可以是绝对目录，如果上级目录不存在会自动创建，没有默认值。-->
    <!--<append>：如果是 true，日志被追加到文件结尾，如果是 false，清空现存文件，默认是true。-->
    <!--<rollingPolicy>:当发生滚动时，决定RollingFileAppender的行为，涉及文件移动和重命名。属性class定义具体的滚动策略类-->
    <!--    class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy"：-->
    <!--        最常用的滚动策略，它根据时间来制定滚动策略，既负责滚动也负责出发滚动。有以下子节点：-->
    <!--        <fileNamePattern>：必要节点，包含文件名及“%d”转换符，“%d”可以包含一个java.text.SimpleDateFormat指定的时间格式，如：%d{yyyy-MM}。-->
    <!--            如果直接使用 %d，默认格式是 yyyy-MM-dd。-->
    <!--            RollingFileAppender的file字节点可有可无，通过设置file，可以为活动文件和归档文件指定不同位置， 当前日志总是记录到file指定的文件（活动文件），-->
    <!--            活动文件的名字不会改变；-->
    <!--            如果没设置file，活动文件的名字会根据fileNamePattern 的值，每隔一段时间改变一次。“/”或者“\”会被当做目录分隔符。-->
    <!--        <maxHistory>: 可选节点，控制保留的归档文件的最大数量，超出数量就删除旧文件。假设设置每个月滚动，且<maxHistory>是6，-->
    <!--            则只保存最近6个月的文件，删除之前的旧文件。注意，删除旧文件是，那些为了归档而创建的目录也会被删除。-->
    <!--    class="ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy"：-->
    <!--        查看当前活动文件的大小，如果超过指定大小会告知RollingFileAppender 触发当前活动文件滚动。只有一个节点:-->
    <!--        <maxFileSize>:这是活动文件的大小，默认值是10MB。-->
    <!--        <prudent>：当为true时，不支持FixedWindowRollingPolicy。支持TimeBasedRollingPolicy，但是有两个限制，1不支持也不允许文件压缩，-->
    <!--            2不能设置file属性，必须留空。-->
    <!--        <triggeringPolicy >: 告知 RollingFileAppender 合适激活滚动。-->
    <!--    class="ch.qos.logback.core.rolling.FixedWindowRollingPolicy"-->
    <!--        根据固定窗口算法重命名文件的滚动策略。有以下子节点：-->
    <!--        <minIndex>:窗口索引最小值-->
    <!--        <maxIndex>:窗口索引最大值，当用户指定的窗口过大时，会自动将窗口设置为12。-->
    <!--        <fileNamePattern>:必须包含“%i”例如，假设最小值和最大值分别为1和2，命名模式为mylog%i.log,会产生归档文件mylog1.log和mylog2.log。-->
    <!--            还可以指定文件压缩选项，例如，mylog%i.log.gz 或者 没有log%i.log.zip-->
    <appender name="ROLLING-FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>${LOG_HOME}/${project-name}.%d.%i.log</fileNamePattern>
            <!--新增关于大小的滚动策略-->
            <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
                <maxFileSize>10MB</maxFileSize>
            </timeBasedFileNamingAndTriggeringPolicy>
            <!--最大保存30天-->
            <maxHistory>30</maxHistory>
        </rollingPolicy>
        <encoder>
            <pattern>${pattern}</pattern>
        </encoder>
    </appender>

    <!--配置固定窗口模式生成日志文件，当文件大于5MB时，生成新的日志文件。窗口大小是1到3，当保存了3个归档文件后，将覆盖最早的日志。-->
    <appender name="FILE-WITH-WINDOW" class="ch.qos.logback.core.rolling.RollingFileAppender">
　　　　　　<file>test.log</file>

　　　　　　<rollingPolicy class="ch.qos.logback.core.rolling.FixedWindowRollingPolicy">
　　　　　　　　　　<fileNamePattern>tests.%i.log.zip</fileNamePattern>
　　　　　　　　　　<minIndex>1</minIndex>
　　　　　　　　　　<maxIndex>3</maxIndex>
　　　　　　</rollingPolicy>

　　　　　　<triggeringPolicy class="ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy">
　　　　　　　　　　<maxFileSize>5MB</maxFileSize>
　　　　　　</triggeringPolicy>
　　　　　　<encoder>
　　　　　　　　　<pattern>${pattern}</pattern>
　　　　　　</encoder>
　　　</appender>

    
    <!--子节点<logger>：用来设置某一个包或具体的某一个类的日志打印级别、以及指定<appender>。-->
    <!--<logger>有name,level和additivity三个属性。 包含零个或多个<appender-ref>元素，标识这个appender将会添加到这个logger-->
    <!--<name>: 指定受此logger约束的某一个包或者具体的某一个类。-->
    <!--<level>: 可选，设置打印级别，大小写无关：TRACE, DEBUG, INFO, WARN, ERROR, ALL和OFF。-->
    <!--    如果未设置此属性，那么当前logger将会继承上级的级别。-->
    <!--    还有一个特殊值INHERITED或者同义词NULL，代表强制执行上级的级别。-->
    <!--<additivity>: 可选，是否向上级logger传递打印信息。默认是true。-->
    <logger name="com.findstar.springbootlearn" level="info" additivity="false">
        <appender-ref ref="CONSOLE"/>
        <appender-ref ref="FILE"/>
    </logger>

    <!--子节点<root>:它是根logger,是所有<logger>的上级。只有一个level属性，因为name已经被命名为"root",是最上级了。 -->
    <!--level: 设置打印级别，大小写无关：TRACE, DEBUG, INFO, WARN, ERROR, ALL和OFF。 默认是DEBUG。-->
    <!--    不能设置为INHERITED或者同义词NULL-->
    <root level="DEBUG">
        <appender-ref ref="CONSOLE-WITH-COLOR"/>
        <appender-ref ref="ROLLING-FILE"/>
    </root>
    
</configuration>

```

参考链接：https://www.cnblogs.com/warking/p/5710303.html

### 参考文献

- https://www.section.io/engineering-education/how-to-choose-levels-of-logging/
- https://www.section.io/engineering-education/how-to-choose-levels-of-logging/





