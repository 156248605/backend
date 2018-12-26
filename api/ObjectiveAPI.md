# backend\src\main\java\com\elex\oa\controller\objectives\GoalController.java:
   15  @CrossOrigin
   16  @Controller
##   @RequestMapping("/goal")
   18  public class GoalController {
   19      @Autowired
   ..
   23  
   24      //获取总公司的数据
##   @RequestMapping("/obtain_data_central")
   26      @ResponseBody
   27      public Map<String,Object> obtainDataCentral() {
   ..
   30  
   31      //获取子公司的数据
##   @RequestMapping("/obtain_data_branch")
   33      @ResponseBody
   34      public Map<String,Object> obtainDataBranch(String unit) {
   ..
   37  
   38      //获取某单位的销售收入详情
##   @RequestMapping("/obtain_revenue_unit")
   40      @ResponseBody
   41      public Goal2 obtainRevenueUnit(String unit) {
   ..
   44  
   45      //获取某单位的销售毛利详情
##   @RequestMapping("/obtain_gross_unit")
   47      @ResponseBody
   48      public Goal2 obtainGrossUnit(String unit) {
   ..
   51  
   52      //获取某单位的销售毛利详情
##   @RequestMapping("/obtain_patent_unit")
   54      @ResponseBody
   55      public Goal obtainPatentUnit(String unit) {
   ..
   58  
   59      //获取公司相应数据(手机端)
##   @RequestMapping("/obtain_company")
   61      @ResponseBody
   62      public List<Map<String,String>> obtainCompany(String unit) {
   ..
   65  
   66      //获取公司销售收入或税后净利相应数据（手机端）
##   @RequestMapping("/obtain_various")
   68      @ResponseBody
   69      public List<Map<String,String>> obtainVarious(String unit, String various) {
   ..
   72  
   73      //获取单位对应的销售收入或税后净利相应数据详情（手机端）
##   @RequestMapping("/obtain_details")
   75      @ResponseBody
   76      public Map<String,String> obtainDetails(String unit, String various) {

# backend\src\main\java\com\elex\oa\controller\objectives\GrossController.java:
   11  @CrossOrigin
   12  @Controller
##   @RequestMapping("/gross")
   14  public class GrossController {
   15      @Autowired
   ..
   17  
   18      //根据年度查询销售毛利信息
##   @RequestMapping("/query_data")
   20      @ResponseBody
   21      public Goal2 queryData(Goal2 goal2) {
   ..
   24  
   25      //保存销售毛利信息
##   @RequestMapping("/save_data")
   27      @ResponseBody
   28      public String saveData(Goal2 goal2) {

# backend\src\main\java\com\elex\oa\controller\objectives\NetController.java:
   11  @Controller
   12  @CrossOrigin
##   @RequestMapping("/net")
   14  public class NetController {
   15      @Autowired
   ..
   17  
   18      //根据年度查询税后净利信息
##   @RequestMapping("/query_data")
   20      @ResponseBody
   21      public Goal2 queryData(Goal2 goal2) {
   ..
   24  
   25      //保存税后净利信息
##   @RequestMapping("/save_data")
   27      @ResponseBody
   28      public String saveData(Goal2 goal2) {

# backend\src\main\java\com\elex\oa\controller\objectives\PatentController.java:
   11  @Controller
   12  @CrossOrigin
##   @RequestMapping("/patent")
   14  public class PatentController {
   15      @Autowired
   ..
   17  
   18      //根据年度查询发明专利信息
##   @RequestMapping("/query_data")
   20      @ResponseBody
   21      public Goal queryData(Goal goal) {
   ..
   24  
   25      //保存发明专利信息
##   @RequestMapping("/save_data")
   27      @ResponseBody
   28      public String saveData(Goal goal) {

# backend\src\main\java\com\elex\oa\controller\objectives\RevenueController.java:
   11  @Controller
   12  @CrossOrigin
##   @RequestMapping("/revenue")
   14  public class RevenueController {
   15      @Autowired
   ..
   17  
   18      //根据年度查询销售收入信息
##   @RequestMapping("/query_data")
   20      @ResponseBody
   21      public Goal2 queryData(Goal2 goal2) {
   ..
   24  
   25      //保存销售收入信息
##   @RequestMapping("/save_data")
   27      @ResponseBody
   28      public String saveData(Goal2 goal2) {

# backend\src\main\java\com\elex\oa\controller\objectives\TargetController.java:
   17  @Controller
   18  @CrossOrigin
##   @RequestMapping("/target")
   20  public class TargetController {
   21  
   ..
   24  
   25      //查询年度信息
##   @RequestMapping("/query_annual")
   27      @ResponseBody
   28      public Map<String,Object> queryAnnual() {
   ..
   31  
   32      //查询销售收入列表信息
##   @RequestMapping("/query_sales_list")
   34      @ResponseBody
   35      public PageInfo<Target> querySalesList(TargetQuery targetQuery, Page page) {
   ..
   38  
   39      //查询税后净利列表信息
##   @RequestMapping("/query_net_list")
   41      @ResponseBody
   42      public PageInfo<Target> queryNetList(TargetQuery targetQuery, Page page) {
   ..
   45  
   46      //查询发明专利列表信息
##   @RequestMapping("/query_invention_list")
   48      @ResponseBody
   49      public PageInfo<Target> queryInventionList(TargetQuery targetQuery, Page page) {
   ..
   52  
   53      //添加销售收入
##   @RequestMapping("/add_sales")
   55      @ResponseBody
   56      public String addSales(Target target) {
   ..
   59  
   60      //添加税后净利
##   @RequestMapping("/add_net")
   62      @ResponseBody
   63      public String addNet(Target target) {
   ..
   66  
   67      //添加发明专利
##   @RequestMapping("/add_invention")
   69      @ResponseBody
   70      public String addInvention(Target target) {
   ..
   73  
   74      //修改销售收入
##   @RequestMapping("/amend_sales")
   76      @ResponseBody
   77      public String amendSales(Target target) {
   ..
   80  
   81      //修改税后净利
##   @RequestMapping("/amend_net")
   83      @ResponseBody
   84      public String amendNet(Target target) {
   ..
   87  
   88      //修改发明专利
##   @RequestMapping("/amend_invention")
   90      @ResponseBody
   91      public String amendInvention(Target target) {
   ..
   94  
   95      //管理总板
##   @RequestMapping("/board_total")
   97      @ResponseBody
   98      public Map<String,Object> boardTotal(String annual) {
   ..
  101  
  102      //管理看板（手机端）
##   @RequestMapping("/board_phone")
  104      @ResponseBody
  105      public List<Map<String, String>> boardPhone() {
  ...
  109  
  110      //管理看板项目部门相关
##   @RequestMapping("/board_department")
  112      @ResponseBody
  113      public List<Target> boardDepartment(String department, String annual) {
  ...
  116  
  117      //查看销售收入或税后净利（手机）
##   @RequestMapping("/board_various")
  119      @ResponseBody
  120      public List<Target> boardVarious(String various) {

36 matches across 6 files
