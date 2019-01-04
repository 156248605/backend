# backend\src\main\java\com\elex\oa\controller\reportObstacles\ObstaclesController.java:

## @RequestMapping("/reportObstacles")

## @RequestMapping("/addObstaclesInfo")
添加报障信息
attachment{
    "code":String 附件编码
    "attachment_address":String 附件地址
}
ObstaclesInfo{
    "username":String 登录ID(必选项)
    "operatingSystem":String 操作系统版本(必选项)
    "browser":String 浏览器版本(必选项)
    "description":String 报障信息描述,(可选项)
    "createtime":String 创建时间
    "versionCore":String OAcore版本
    "versionBackend":String OAbackend版本
    "versionFront":String OAfront版本
    "state":String 报障状态
    "attachmentList":[attachment]附件
}
RespUtil{
    "head":{
        "rspCode":String 响应编码
        "rspMsg":String 响应信息
    }
    "body":{返回对象}
}
### Request.json
{
   ObstaclesInfo ,
   "attachment"+i:binary 附件（可多选）,
   "attachmentSize":Integer 附件数量 
}

### ResponseBody
{
    RespUtil
}
