# backend\src\main\java\com\elex\oa\controller\reportObstacles\ObstaclesController.java:

## @RequestMapping("/reportObstacles")

## @RequestMapping("/addObstaclesInfo")
添加报障信息
ObstaclesInfo{
    "username":String 登录ID
    "createtime":String 创建时间
    "platform":String 环境平台
    "versionCore":String OAcore版本
    "versionBackend":String OAbackend版本
    "versionFront":String OAfront版本
    "description":String 报障信息描述
    "attachment"+i:String 附件（可多选）
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
   ObstaclesInfo 
}

### ResponseBody
{
    RespUtil
}
