# Game_0_demo README.md

~不知道恋恋会不会是"show~ ~me~ ~the~ ~code"类型的不看文档，总之我边学边做做了几天，人有点迷糊，可能都已经忘掉了一些前面写的代码的细节，以及本身文字功底就差，这文档总监怕是会看的很糟心了，先在这致歉了😣~



> 感觉我什么都不会啊啊啊😭

<div style="text-align: right;">
	<img src="http://pic.zsgbp.site/temp/neuroandevilaroundthecorner.jpg" alt="我也不会html" width="80" height="85">
    <p style="text-align: right;font-size: 12px">放个双子在这看看会发生什么</p>
</div>        

-----


### 先上接口说明

虽然我是用了[Open API](http://localhost:8888/swagger-ui/index.html#/)的，不过也是因为我的命名方式不统一，不规范，可能不怎么一目了然，所以在此对各接口做说明

~会不会还是说不明白啊~



#### 1. **用户相关** 

请求路径统一以  /user  为前缀

| 请求路径         | 请求方式 | 传递参数[^ 1]及说明                                          | 实现功能/返回参数及说明                                      | 其他说明                                                     |
| ---------------- | -------- | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| /regist          | POST     | `"name"` : 用户名字,只能使用字母数字和下划线,上限100个字符,整个数据库中唯一<br />`"email"` : 用户邮箱,未实现验证有效性,上限100个字符,唯一<br />`"password"` : 用户密码,只能使用字母数字和`/` `!` `#` `$` `*` ,上限100<br />`"confirm"` : 确认密码<br />`"autoLogin"` : (可选) 是否注册成功自动登录 | 注册<br />注册成功自动用递增的主键作为用户id,并返回用户基本信息,以及是否自动登录成功,否则返回注册失败原因 | `"autoLogin"`默认为true,可在配置文件中更改<br />             |
| /delete          | POST     | `"nameOrEmail"` : 用以确认要删除的账号<br />`"password"` : 用以防止误删 | 删号退游[^3]                                                 | 需要 登录/token[^ 2]<br />实现`"nameOrEmail"`既可以填name也可以填email实际上用的是检测是否含有`@`字符,有待改进 |
| /login           | POST     | `"nameOrEmail"` : 如名<br />`"password"` : 如名              | 登入<br />返回用户基本和 "详细" 信息及token                  | token有效期1天,存在`HttpServletResponse`类 cookie 里，不过我似乎还在所谓的"详细"信息里包含了token,这就是多次更改拿不定主意的结果啊 |
| /logout          | POST     | 无                                                           | 登出                                                         | 需要 登录/token                                              |
| /update/password | POST     | `"originalInfo"` : 原密码<br />`"updateInfo"` : 修改密码<br />`"confirm"` : 确认修改密码 | 修改密码                                                     | 需要 登录/token                                              |
| /update/name     | POST     | `"originalInfo"` : 应留空<br />`"updateInfo"` : 修改名字<br />`"confirm"` : 应留空 | 修改用户名                                                   | 需要 登录/token<br />关于应留空：因为直接偷懒用的和上面那个一样的请求类，包括下面也是 |
| /update/sign     | POST     | `"originalInfo"` : 应留空<br />`"updateInfo"` : 修改签名<br />`"confirm"` : 应留空 | 修改签名                                                     | 需要 登录/token                                              |
| /ask             | POST     | `"ask1"` : 第一个查询的用户名<br />`"ask2"` : 第二个查询的用户名<br />`"ask3"` : 第三个查询的用户名<br />.... | 查询用户登陆状态<br />按ask后面的字段的自然顺序返回用户的信息,包括是否在线,或者上一次交互时间 | 将忽略非ask开头的参数<br />上一次交互时间可能是,登入,登出,修改等 |
| /info            | GET      | `askRank` : 可选,可以是 <br />"*all*" : 表示查询存在的所有排名信息<br />"*rankGuess*" : 表示猜数字小游戏的rank信息<br />"*rank0:1,2,3...*" : 表示bl0ck小游戏的对应map0编号的rank信息,如果是"rank0:all",表示参加的所有的map0的排名信息 | 查询用户基本信息<br />同时可以查询用户排名信息               | 后面加的<br />用GET请求似乎<br />关于`Rank`类,首先这个名字取得就不好,这个类其实还有很多遗留问题未解决,不过没时间啦,能跑就行🙏 |

-----

#### 2. **小游戏 bl0ck 相关**

请求统一以 /bl0ck 为前缀

<div style="text-align: left;">
    <figure>
        <img src="http://pic.zsgbp.site/temp/bl0ck-controller-joke.png" alt="bl-0ck" width="220" height="50">
        <figcaption>绷</figcaption>
    </figure>
</div>

| 请求路径       | 请求方式 | 传递参数及说明                                               | 实现功能/返回参数及说明                                     | 其他说明                                                     |
| -------------- | -------- | ------------------------------------------------------------ | ----------------------------------------------------------- | ------------------------------------------------------------ |
| /{map0Id}      | POST     | *{map0Id}*为地图id,范围为1到1<<32 -1即Integer的正数部分<br />`"size"` : 可选,设置创建地图后返回的初始化地图的大小<br />`"seed"` : 可选,用于生成地图的种子 | 创建一张map0                                                | 以下请求全都为 需要登录<br />初始化地图大小为$(2 \cdot size +1)^2$<br />默认`"size"`可在配置文件中更改,默认`"seed"`由程序自动生成 |
| /{map0Id}/     | POST     | *{map0Id}*为地图id<br />`"x"` : 选取方块的X坐标<br />`"y"` : 选取方块的Y坐标<br />`"autoCreateMap0"`: 可选,是否自动创建地图<br />`"size"` : 可选,设置创建地图后返回的初始化地图的大小<br />`"seed"` : 可选,用于生成地图的种子 | van♂游戏                                                    | `"autoCreateMap0"`默认为`true`,不可在配置文件内更改😩         |
| /{map0Id}/view | POST     | {map0Id}为地图id<br />`"x"` : 中心方块的X坐标<br />`"y"` : 中心方块的Y坐标<br />`"autoCreateMap0"`: 可选,是否自动创建地图<br />`"size"` : 可选<br />`"seed"` : 可选,用于生成地图的种子 | 返回以(`x`,`y`)为中心的`size`大小的地图{map0Id}上的方块信息 | 暂时未对Integer类的`"size"`设置合理的上限,或许这是前端✌一根手指的事?👆 |
| /{map0Id}/l00k | POST     | 参数同上                                                     | 返回返回以(`x`,`y`)为中心的`size`大小的地图                 | 不应当存在                                                   |

关于这个游戏的规则,说简单也不简单,不过我想也许应该用[另一篇文档](mark.md)来展示

-----

#### 3. 小游戏 猜数字[^4] 相关 

请求统一以 /guess 为前缀

| 请求路径        | 请求方式 | 传递参数及说明         | 实现功能/返回参数及说明                                      | 其他说明                                                   |
| --------------- | -------- | ---------------------- | ------------------------------------------------------------ | ---------------------------------------------------------- |
| /start          | GET      | 无                     | 开始一局猜数字游戏                                           | 额,是不是也不应当存在                                      |
| /guess/{number} | GET      | {number}即为你猜的数字 | 返回猜大了还是小了以及猜的次数<br />如果猜对了返回得分信息和前面所有发送猜的数据<br />并记录得分到排名系统 | 并删除所有该用户进行猜数字的临时信息(这下知道体谅数据库了) |
| /guess/giveup   | GET      | 无                     | 放弃这局游戏                                                 | 懦夫才用                                                   |

关于这个游戏的规则 ~实际上只是加了一点gambling元素~

- 随机生成的数字的范围并不小于1 << 11 ,不过具体是多少不重要,但愿不会超long, 因为事实上我原先的代码是使用的字符数组来表示这个数的,不过后面考虑到玩家可能需要手打很长一串数值未免有点,所以改了

- 设总共进行猜数字n次, 在有十次猜数字的机会并将剩余次数作为得分的基础规则上我加入了放弃的选择,  并开放了十次以后的机会 ,不过在此之后放弃将会扣除$n-10$分, 在此之后如果猜对数字将会随机获得$[-n,n]$ 之间的整数的分数, 

- 也就是说 所有, 或者一无所有! [^5]

  

-----



### 然后能来看看吗

​	首先**我是真的**什么都不会，我只是一个来自穷乡僻壤的在初中学了一点点c++搞oi的蒟蒻，所以在此之前即使很想去实现一些具体的软件或游戏也没有相应的技能和大把的时间去学习，以至于对一个项目实现的基本组成都不甚了解，我有尝试过去学图形库easyX,openGL什么的，甚至还想过学unity，不过在这个不管是哪里的高中都卷的莫名激烈的时代和一对传统父母的威逼之下，被冠以不务正业的名号并收回电脑使用权也是再正常不过了，当然公平的来说，可能还有因为懒。

​	尽管我是真的**什么都不会**，我还是挺愿意并能折腾的，这次的后端大作业就把我狠狠的折腾了，以下是一些对于我这次作业中的一些说明和应当做的改进以及感悟

1. 项目名字真不要乱取，类名字真不要乱取，变量名字更是不要乱取，希望多做点就能够养成习惯吧
2. 依赖里可能存在部分无效依赖，不过不敢删
3. lombok及idea内对应的插件未能正确识别@Data等注解的问题尚未解决
4. 对于每个层的分工尚未清晰，有的Controller仍有大量重复代码
5. 事实上可能其他层也有，事实上我甚至还边写文档边改代码
6. 为了防止写代码时被中英文切换整红温，我使用了系统的英文键盘输入法，这导致几乎所有的注释都是乱写的英文
7. 大量注释事实上无效
8. 处理bl0ck里连续0相接时的代码尤为重灾区，因为测试时间和方式受限，可能仍存在值得重写整部分代码的漏洞
9. 未能解决从配置文件里读取数据的规范化
10. util里的Coord类不应存在
11. 许多类或接口不应存在或不应仅用@Deprecated注解
12. util里的CreateStuff类即为我自己写的一些工具类，未做好分类
13. Bl0ckCacheService类里不应当存在大量其他类的数据库操作方法
14. 对redis的使用仅做了基础了解
15. 虽然之前会一点点c++，不过因为时搞oi对OOP一无所知，这次写代码时才学到了许多类相关的所谓的奇技淫巧，比如set之后返回自己，
16. 前面写的类中应当加入比如上一条的set方法用以简化操作

​	最后我是真的什么都不会，不过无论如何，无论我能不能进入后端，学线后端学长们提供的这三次培训的文档和讲解，以及我做作业的过程都无疑在事实上对我帮助巨大，我也会像恋恋说过的那样继续热爱创造



-----

~挺尬的，真的能有人读到这吗~

~谢谢你HEART<3<3<3~

<table>
  <tr>
    <td><img src="http://pic.zsgbp.site/temp/neuroaround.webp" alt="Image 1" width="480" height=300></td>
    <td><img src="http://pic.zsgbp.site/temp/evilaround.webp" alt="Image 2"width="480" height=300"></td>
  </tr>
</table>













[^ 1]: 一般的显式参数
[^ 2]: 我使用的验证方式是需要用户提供正确的token并且在已登录名单内，需要验证的API的路径可在配置文件中调整
[^3]:  事实上还并没有做删除游戏排名信息的处理,不过排名是按照id来存的而id是用户不可选的所以应该还好,不过最好还是加上删除所有相关数据才好
[^4]: 临时赶的,或许我该拿时间去debug上面那个而不是交上两个都bug超多的游?戏?
[^5]: 老米娇妻了
