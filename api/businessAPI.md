
# backend\src\main\java\com\elex\oa\controller\hr\ChangeInformationController.java:
   38       *@Date: 11:48 2018\4\13 0013
   39       */
##   @RequestMapping("/queryChangeInformations")
   41      @ResponseBody
   42      public PageInfo<ChangeInformation> queryChangeInformations(
   ..
   58       *@Date: 10:46 2018\5\25 0025
   59       */
##   @RequestMapping("/queryAllChangeInformations")
   61      @ResponseBody
   62      public List<ChangeInformation> queryAllChangeInformations(){
   ..
   70       *@Date: 10:57 2018\5\25 0025
   71       */
##   @RequestMapping("/deleteChangeinformationByIds")
   73      @ResponseBody
   74      public String deleteChangeinformationByIds(
   ..
   90       *@Date: 10:59 2018\5\25 0025
   91       */
##   @RequestMapping("/importChangeinformations")
   93      @ResponseBody
   94      public String importChangeinformations(

# backend\src\main\java\com\elex\oa\controller\hr\ContractInformationController.java:
   51       * @Date: 16:08 2018\4\9 0009
   52       */
##   @RequestMapping("/queryContractInformations")
   54      @ResponseBody
   55      public PageInfo<ContractInformation> queryContractInformations(
   ..
   94       * @Date: 16:05 2018\5\30 0030
   95       */
##   @RequestMapping("/queryContractsByUserid")
   97      @ResponseBody
   98      public List<ContractInformation> queryContractsByUserid(
   ..
  113       * @Date: 13:29 2018\4\10 0010
  114       */
##    @RequestMapping("/queryOneContractInformation")
  116      @ResponseBody
  117      public List<RenewContractRecord> queryOneContractInformation(@RequestParam("contractId") int contractId) {
  ...
  131       * @Date: 17:21 2018\4\12 0012
  132       */
##    @RequestMapping("/queryContractsById")
  134      @ResponseBody
  135      public ContractInformation queryContractsById(
  ...
  145       * @Date: 14:16 2018\4\20 0020
  146       */
##    @RequestMapping("/addContractInformation")
  148      @ResponseBody
  149      public String addContractInformation(
  ...
  183       * @Date: 9:59 2018\5\29 0029
  184       */
##    @RequestMapping("/updateContractInformation")
  186      @ResponseBody
  187      public String updateContractInformation(
  ...
  242       * @Date: 16:01 2018\5\25 0025
  243       */
##    @RequestMapping("/queryAllContractInformations")
  245      @ResponseBody
  246      public List<ContractInformation> queryAllContractInformations() throws ParseException {
  ...
  275       * @Date: 10:40 2018\5\29 0029
  276       */
##    @RequestMapping("/deleteContractsByIds")
  278      @ResponseBody
  279      public String deleteContractsByIds(
  ...
  291       * @Date: 11:04 2018\5\29 0029
  292       */
##    @RequestMapping("/importContractinformations")
  294      @ResponseBody
  295      public String importContractinformations(

# backend\src\main\java\com\elex\oa\controller\hr\DepartmentInformationController.java:
   64       *@Date: 13:20 2018\3\16 0016
   65       */
##   @RequestMapping("/queryOneDepByDepname")
   67      @ResponseBody
   68      public Dept queryOneDepByDepname(@RequestParam("title") String depname){
   ..
  125       *@Date: 19:37 2018\8\11 0011
  126       */
##    @RequestMapping("/queryOneDepByDepcode")
  128      @ResponseBody
  129      public Dept queryOneDepByDepcode(
  ...
  188       *@Date: 11:00 2018\4\11 0011
  189       */
##    @RequestMapping("/queryDepartments")
  191      @ResponseBody
  192      public List<Dept> queryDepartments(){
  ...
  210       *@Date: 17:49 2018\8\30 0030
  211       */
##    @RequestMapping("/queryDepartmentsRemoveChilren")
  213      @ResponseBody
  214      public List<Dept> queryDepartmentsRemoveChilren(
  ...
  224       *@Date: 10:43 2018\4\16 0016
  225       */
##    @RequestMapping("/listDepts")
  227      @ResponseBody
  228      public DeptTree listDepts(){
  ...
  264       *@Date: 10:50 2018\4\23 0023
  265       */
##    @RequestMapping("/addOneDepartment")
  267      @ResponseBody
  268      public Object addOneDepartment(
  ...
  497       *@Date: 9:30 2018\5\2 0002
  498       */
##    @RequestMapping("/queryOneByDepid")
  500      @ResponseBody
  501      public Dept queryOneByDepid(@RequestParam("depid") Integer id){
  ...
  517       *@Date: 9:55 2018\5\2 0002
  518       */
##    @RequestMapping("/updateOneDepartment")
  520      @ResponseBody
  521      public Object updateOneDepartment(
  ...
  938       *@Date: 13:39 2018\5\2 0002
  939       */
##    @RequestMapping("/deleteDeptsById")
  941      @ResponseBody
  942      public String deleteDeptsById(@RequestParam("id") Integer id){
  ...
  973       *@Date: 17:13 2018\5\2 0002
  974       */
##    @RequestMapping("/queryDeptLogInformations")
  976      @ResponseBody
  977      public PageInfo<DeptLog> queryDeptLogInformations(
  ...
 1009       *@Date: 9:18 2018\5\24 0024
 1010       */
##     @RequestMapping("/queryAllDeptLogInformations")
 1012      @ResponseBody
 1013      public List<DeptLog> queryAllDeptLogInformations(){
 ....
 1029       *@Date: 10:50 2018\5\24 0024
 1030       */
##     @RequestMapping("/deleteDeplogByIds")
 1032      @ResponseBody
 1033      public String deleteDeplogByIds(
 ....
 1049       *@Date: 15:09 2018\5\7 0007
 1050       */
##     @RequestMapping("/importDeploginformations")
 1052      @ResponseBody
 1053      public String importDeploginformations(
 ....
 1080       *@Date: 11:15 2018\6\15 0015
 1081       */
##     @RequestMapping("/sortDepinformation")
 1083      @ResponseBody
 1084      public List<TitleAndCode> sortDepinformation(
 ....
 1106       *@Date: 14:52 2018\6\15 0015
 1107       */
##     @RequestMapping("/submitSortdata")
 1109      @ResponseBody
 1110      public DeptTree submitSortdata(
 ....
 1200       *@Date: 14:16 2018\6\28 0028
 1201       */
##     @RequestMapping("/queryHRManageCard")
 1203      @ResponseBody
 1204      public Object queryHRManageCard(
 ....
 1224       *@Date: 15:53 2018\8\15 0015
 1225       */
##     @RequestMapping("/queryHRManageCard2")
 1227      @ResponseBody
 1228      public Object queryHRManageCard2(
 ....
 1264       *@Date: 15:55 2018\8\15 0015
 1265       */
##     @RequestMapping("/queryHRManageCard3")
 1267      @ResponseBody
 1268      public Object queryHRManageCard3(
 ....
 1304       *@Date: 15:56 2018\8\15 0015
 1305       */
##     @RequestMapping("/queryHRManageCard4")
 1307      @ResponseBody
 1308      public Object queryHRManageCard4(
 ....
 1344       *@Date: 15:57 2018\8\15 0015
 1345       */
##     @RequestMapping("/queryHRManageCard5")
 1347      @ResponseBody
 1348      public Object queryHRManageCard5(
 ....
 1376       *@Date: 15:58 2018\8\15 0015
 1377       */
##     @RequestMapping("/queryHRManageCard6")
 1379      @ResponseBody
 1380      public Object queryHRManageCard6(
 ....
 1408       *@Date: 15:59 2018\8\15 0015
 1409       */
##     @RequestMapping("/queryHRManageCard7")
 1411      @ResponseBody
 1412      public Object queryHRManageCard7(
 ....
 1440       *@Date: 10:52 2018\6\29 0029
 1441       */
##     @RequestMapping("/queryIOSData")
 1443      @ResponseBody
 1444      public HRManageCard queryIOSData(
 ....
 1454       *@Date: 10:08 2018\7\16 0016
 1455       */
##     @RequestMapping("/validateDeptnameForAddDept")
 1457      @ResponseBody
 1458      public Boolean validateDeptnameForAddDept(
 ....
 1472       *@Date: 10:12 2018\7\16 0016
 1473       */
##     @RequestMapping("/validateDeptcodeForAddDept")
 1475      @ResponseBody
 1476      public Boolean validateDeptcodeForAddDept(

# backend\src\main\java\com\elex\oa\controller\hr\DimissionController.java:
   52       *@Date: 17:00 2018\4\16 0016
   53       */
##   @RequestMapping("/addDimission")
   55      @ResponseBody
   56      public Object addDimission(
   ..
   90       *@Date: 18:01 2018\4\16 0016
   91       */
##   @RequestMapping("/queryDimissionInformations")
   93      @ResponseBody
   94      public PageInfo<DimissionInformation> queryDimissionInformations(
   ..
  110       *@Date: 15:05 2018\5\29 0029
  111       */
##    @RequestMapping("/queryAllDimissionInformations")
  113      @ResponseBody
  114      public List<DimissionInformation> queryAllDimissionInformations(){
  ...
  122       *@Date: 13:35 2018\4\17 0017
  123       */
##    @RequestMapping("/modifyDimissionInformationById")
  125      @ResponseBody
  126      public Object modifyDimissionInformationById(
  ...
  136       *@Date: 11:18 2018\5\30 0030
  137       */
##    @RequestMapping("/queryDimissionInformationById")
  139      @ResponseBody
  140      public DimissionInformation queryDimissionInformationById(
  ...
  158       *@Date: 13:39 2018\5\30 0030
  159       */
##    @RequestMapping("/removeDimissionInformations")
  161      @ResponseBody
  162      public Object removeDimissionInformations(
  ...
  189       *@Date: 11:03 2018\6\1 0001
  190       */
##    @RequestMapping("/importDimissionInformations")
  192      @ResponseBody
  193      public String importDimissionInformations(

# backend\src\main\java\com\elex\oa\controller\hr\PersonalInformationController.java:
   65  
   66  
##   @RequestMapping("/PersonalInformationController/queryPersonalInformations")
   68      @ResponseBody
   69      public Map<String,Object> getInitPage(
   ..
   97       * @Date: 9:49 2018\4\8 0008
   98       */
##   @RequestMapping("/queryPersonalInformations")
  100      @ResponseBody
  101      public PageInfo<PersonalInformation> queryPersonalInformation(@RequestParam("page") Integer page,
  ...
  223       * @Date: 18:02 2018\4\8 0008
  224       */
##    @RequestMapping("/queryPersonalInformationById")
  226      @ResponseBody
  227      public PersonalInformation queryPersonalInformationById(
  ...
  250       * @Date: 18:02 2018\4\8 0008
  251       */
##    @RequestMapping("/queryPersonalInformationByUserid")
  253      @ResponseBody
  254      public ArrayList<HashMap> queryPersonalInformationByUserid(
  ...
  306       * @Date: 18:02 2018\4\8 0008
  307       */
##    @RequestMapping("/queryPersonalInformationByTruename")
  309      @ResponseBody
  310      public PersonalInformation queryPersonalInformationByTruename(
  ...
  520       * @Date: 18:07 2018\4\10 0010
  521       */
##    @RequestMapping(value = "/addBaseInformation", consumes = "multipart/form-data")
  523      @ResponseBody
  524      public Object addBaseInformation(
  ...
  772       * @Date: 11:36 2018\4\11 0011
  773       */
##    @RequestMapping("/addManageInformation")
  775      @ResponseBody
  776      public Object addManageInformation(
  ...
  823       * @Date: 17:41 2018\4\11 0011
  824       */
##    @RequestMapping("/addCostInformation")
  826      @ResponseBody
  827      public String addCostInformation(
  ...
  901       * @Date: 18:42 2018\4\11 0011
  902       */
##    @RequestMapping("/addOtherInformation")
  904      @ResponseBody
  905      public Object addOtherInformation(
  ...
  944       * @Date: 13:59 2018\4\12 0012
  945       */
##    @RequestMapping("/updateBaseInformation")
  947      @ResponseBody
  948      public Object updateBaseInformation(
  ...
 1309       * @Date: 15:59 2018\4\12 0012
 1310       */
##     @RequestMapping("/updateManageInformation")
 1312      @ResponseBody
 1313      public Object updateManageInformation(
 ....
 1456       * @Date: 16:28 2018\4\12 0012
 1457       */
##     @RequestMapping("/updateCostInformation")
 1459      @ResponseBody
 1460      public String updateCostInformation(
 ....
 1641       * @Date: 16:46 2018\4\12 0012
 1642       */
##     @RequestMapping("/updateOtherInformation")
 1644      @ResponseBody
 1645      public Object updateOtherInformation(
 ....
 1783       * @Date: 16:40 2018\4\18 0018
 1784       */
##     @RequestMapping("/queryPersonalInformationsByDepid")
 1786      @ResponseBody
 1787      public List<PersonalInformation> queryPersonalInformationsByDepid(
 ....
 1805       * @Date: 17:23 2018\4\18 0018
 1806       */
##     @RequestMapping("/queryPersonalInformationsByNull")
 1808      @ResponseBody
 1809      public List<PersonalInformation> queryPersonalInformationsByNull() throws ParseException {
 ....
 1977       * @Date: 15:09 2018\5\7 0007
 1978       */
##     @RequestMapping("/importPersonalInformations")
 1980      @ResponseBody
 1981      public Object importPersonalInformations(
 ....
 2468       * @Date: 13:56 2018\5\10 0010
 2469       */
##     @RequestMapping("/deleteInformationsByIds")
 2471      @ResponseBody
 2472      public Object deleteInformationsByIds(
 ....
 2539       * @Param
 2540       **/
##     @RequestMapping("/deleteAllInformations")
 2542      @ResponseBody
 2543      public Object deleteAllInformations(
 ....
 2568       * @Date: 14:15 2018\7\9 0009
 2569       */
##     @RequestMapping("/queryGXF001")
 2571      @ResponseBody
 2572      public List<Object> queryGXF001() throws ParseException {
 ....
 2604       * @Date: 14:33 2018\7\9 0009
 2605       */
##     @RequestMapping("/queryGXF002")
 2607      @ResponseBody
 2608      public List<Object> queryGXF002() {
 ....
 2623       * @Date: 14:38 2018\7\9 0009
 2624       */
##     @RequestMapping("/queryGXF003")
 2626      @ResponseBody
 2627      public List<Object> queryGXF003() {
 ....
 2642       * @Date: 11:34 2018\8\10 0010
 2643       */
##     @RequestMapping("/queryGXF004")
 2645      @ResponseBody
 2646      public String queryGXF004(
 ....
 2674       * @Date: 10:50 2018\8\21 0021
 2675       */
##     @RequestMapping("/queryGXF005")
 2677      @ResponseBody
 2678      public Object queryGXF005() {
 ....
 2696       * @Date: 11:08 2018\8\21 0021
 2697       */
##     @RequestMapping("/queryGXF006")
 2699      @ResponseBody
 2700      public Object queryGXF006(
 ....
 2715       * @Date: 14:33 2018\7\9 0009
 2716       */
##     @RequestMapping("/queryGXF007")
 2718      @ResponseBody
 2719      public List<Object> queryGXF007() {
 ....
 2736       * @Date: 14:55 2018\9\8 0008
 2737       */
##     @RequestMapping("/queryGXF008")
 2739      @ResponseBody
 2740      public Object queryGXF008(
 ....
 2757       * @Date: 13:39 2018\9\25 0025
 2758       */
##     @RequestMapping("/queryHLT001")
 2760      @ResponseBody//表示该方法的返回结果直接写入 HTTP response body 中，一般在异步获取数据时使用【也就是AJAX】
 2761      public Object queryHLT001(
 ....
 2770       * @Date: 18:30 2018\7\17 0017
 2771       */
##     @RequestMapping("/queryZHG001")
 2773      @ResponseBody
 2774      public Object queryZHG001(
 ....
 2799       * @Date: 15:51 2018\8\21 0021
 2800       */
##     @RequestMapping("/queryZHG002")
 2802      @ResponseBody
 2803      public Object queryZHG002(
 ....
 2821       * @Date: 9:40 2018\8\3 0003
 2822       */
##     @RequestMapping("/queryWriteGzrz")
 2824      @ResponseBody
 2825      public Object queryWriteGzrz(
 ....
 2843       * @Date: 14:52 2018\8\3 0003
 2844       */
##     @RequestMapping("/queryApproveGzrz")
 2846      @ResponseBody
 2847      public Object queryApproveGzrz(
 ....
 2865       * @Date: 15:23 2018\9\28 0028
 2866       */
##     @RequestMapping("/queryAllLysqd")
 2868      @ResponseBody
 2869      public List<Lysqd> queryAllLysqd() {

# backend\src\main\java\com\elex\oa\controller\hr\PostInformationController.java:
   49       *@Date: 10:40 2018\3\20 0020
   50       */
##   @RequestMapping("/queryOnePostByPostname")
   52      @ResponseBody
   53      public Post queryOnePostByPostname(@RequestParam("name") String name){
   ..
   77       *@Date: 10:40 2018\3\20 0020
   78       */
##   @RequestMapping("/queryOnePostByPostid")
   80      @ResponseBody
   81      public Post queryOnePostByPostid(@RequestParam("id") Integer id){
   ..
  104       *@Date: 19:48 2018\8\11 0011
  105       */
##    @RequestMapping("/queryOnePostByPostcode")
  107      @ResponseBody
  108      public Post queryOnePostByPostcode(
  ...
  120       *@Date: 11:14 2018\4\11 0011
  121       */
##    @RequestMapping("/queryPosts")
  123      @ResponseBody
  124      public List<Post> queryPosts(){
  ...
  142       *@Date: 13:39 2018\8\30 0030
  143       */
##    @RequestMapping("/queryPostsRemoveChilren")
  145      @ResponseBody
  146      public List<Post> queryPostsRemoveChilren(
  ...
  156       *@Date: 9:37 2018\4\23 0023
  157       */
##    @RequestMapping("/listPosts")
  159      @ResponseBody
  160      public DeptTree listPosts(){
  ...
  193       *@Date: 11:55 2018\4\23 0023
  194       */
##    @RequestMapping("/addOnePost")
  196      @ResponseBody
  197      public Object addOnePost(
  ...
  230       *@Date: 10:44 2018\5\2 0002
  231       */
##    @RequestMapping("/queryByPostid")
  233      @ResponseBody
  234      public Post queryByPostid(@RequestParam("id") Integer id){
  ...
  244       *@Date: 11:03 2018\5\2 0002
  245       */
##    @RequestMapping("/updateOnePost")
  247      @ResponseBody
  248      public Object updateOnePost(
  ...
  394       *@Date: 14:44 2018\5\2 0002
  395       */
##    @RequestMapping("/deletePostsById")
  397      @ResponseBody
  398      public Object deletePostsById(@RequestParam("id") Integer id){
  ...
  429       *@Date: 10:02 2018\5\3 0003
  430       */
##    @RequestMapping("/queryPostLogInformations")
  432      @ResponseBody
  433      public PageInfo<PostLog> queryPostLogInformations(
  ...
  459       *@Date: 9:18 2018\5\24 0024
  460       */
##    @RequestMapping("/queryAllPostLogInformations")
  462      @ResponseBody
  463      public List<PostLog> queryAllPostLogInformations(){
  ...
  479       *@Date: 10:50 2018\5\24 0024
  480       */
##    @RequestMapping("/deletePostlogByIds")
  482      @ResponseBody
  483      public String deletePostlogByIds(
  ...
  499       *@Date: 15:09 2018\5\7 0007
  500       */
##    @RequestMapping("/importPostloginformations")
  502      @ResponseBody
  503      public String importPostloginformations(
  ...
  530       *@Date: 11:15 2018\6\15 0015
  531       */
##    @RequestMapping("/sortPostinformation")
  533      @ResponseBody
  534      public List<TitleAndCode> sortPostinformation(
  ...
  552       *@Date: 14:52 2018\6\15 0015
  553       */
##    @RequestMapping("/submitSortdata2")
  555      @ResponseBody
  556      public DeptTree submitSortdata2(

# backend\src\main\java\com\elex\oa\controller\hr\PostRelationshipController.java:
   26  @CrossOrigin
   27  @Controller
##RequestMapping("/postrelationship")
   29  public class PostRelationshipController {
   30      @Autowired
   ..
   41       **/
   42  
##   @RequestMapping("/addPostrelationship")
   44      @ResponseBody
   45      public String addPostrelationship(
   ..
   57       * @return
   58       **/
##   @RequestMapping("/queryAllPostrelationship")
   60      @ResponseBody
   61      public List<Postlevelrelationshipinfo> queryAllPostrelationship(){
   ..
   70       * @return
   71       **/
##   @RequestMapping("/removePostrelationship")
   73      @ResponseBody
   74      public Object removePostrelationship(

# backend\src\main\java\com\elex\oa\controller\hr\RAndPInformationController.java:
   32       *@Date: 9:35 2018\4\18 0018
   33       */
##   @RequestMapping("/addRAndPInformation")
   35      @ResponseBody
   36      public String addRAndPInformation(
   ..
   52       *@Date: 20:52 2018\4\18 0018
   53       */
##   @RequestMapping("/queryRAndPProjects")
   55      @ResponseBody
   56      public List<RAndPInformation> queryRAndPProjects(){
   ..
   64       *@Date: 10:22 2018\4\19 0019
   65       */
##   @RequestMapping("/queryRAndPInformations")
   67      @ResponseBody
   68      public PageInfo<RAndPInformation> queryRAndPInformations(
   ..
   85       *@Date: 16:14 2018\4\19 0019
   86       */
##   @RequestMapping("/queryRAndPInformationById")
   88      @ResponseBody
   89      public RAndPInformation queryRAndPInformationById(
   ..
   99       *@Date: 18:18 2018\4\19 0019
  100       */
##    @RequestMapping("/updateRAndPInformationById")
  102      @ResponseBody
  103      public String updateRAndPInformationById(
  ...
  113       *@Date: 19:39 2018\4\19 0019
  114       */
##    @RequestMapping("/deleteRAndPInformationByIds")
  116      @ResponseBody
  117      public String deleteRAndPInformationByIds(String ids){
  ...
  128       *@Date: 9:54 2018\4\20 0020
  129       */
##    @RequestMapping("/queryRAndPInformationByUserid")
  131      @ResponseBody
  132      public List<RAndPInformation> queryRAndPInformationByUserid(

# backend\src\main\java\com\elex\oa\controller\hr\UserController.java:
   41       **/
   42  
##   @RequestMapping("/updateEmployeenumber")
   44      @ResponseBody
   45      public String updateEmployeenumber(){
   ..
   57       *@Date: 11:58 2018\4\12 0012
   58       */
##   @RequestMapping("/queryUserByUserId")
   60      @ResponseBody
   61      public User queryUserByUserId(
   ..
   71       *@Date: 15:38 2018\4\16 0016
   72       */
##   @RequestMapping("/queryAllUsers")
   74      @ResponseBody
   75      public List<User> queryAllUsers(){
   ..
   83       *@Date: 10:03 2018\8\21 0021
   84       */
##   @RequestMapping("/queryAllServings")
   86      @ResponseBody
   87      public List<PersonalInformation> queryAllServings(){

108 matches across 9 files


##hing 12 files for "@RequestMapping("

# backend\src\main\java\com\elex\oa\controller\eqptController\ExcelController.java:
   15  @Controller
   16  @CrossOrigin
##RequestMapping("/excel")
   18  public class ExcelController {
   19  
   ..
   22  
   23      //导入excel
##   @RequestMapping("/importMaterial")
   25      @ResponseBody
   26      public Map<String, Object> importExcelMaterial(@RequestParam(value="file",required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response){
   ..
   31      }
   32  
##   @RequestMapping("/importRepository")
   34      @ResponseBody
   35      public Map<String, Object> importExcelRepository(@RequestParam(value="file",required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response){
   ..
   40      }
   41  
##   @RequestMapping("/importPartner")
   43      @ResponseBody
   44      public Map<String, Object> importExcelPartner(@RequestParam(value="file",required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response){
   ..
   49      }
   50  
##   @RequestMapping("/importLinkman")
   52      @ResponseBody
   53      public Map<String, Object> importExcelLinkman(@RequestParam(value="file",required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response){

# backend\src\main\java\com\elex\oa\controller\eqptController\InRepositoryController.java:
   20  @CrossOrigin
   21  @Controller
##RequestMapping("/in")
   23  public class InRepositoryController {
   24  
   ..
   26      public InRepositoryImpl inRepositoryImpl;
   27  
##   @RequestMapping("/find")
   29      @ResponseBody
   30      public PageInfo<Repository> ShowRepository (Page page, HttpServletRequest request) {
   ..
   33      }
   34  
##   @RequestMapping("/new")
   36      @ResponseBody
   37      public String InsertRepository (HttpServletRequest request) throws ParseException {
   ..
   39      }
   40  
##   @RequestMapping("/search")
   42      @ResponseBody
   43      public PageInfo<Repository> searchRepository(Page page, HttpServletRequest request){
   ..
   46      }
   47  
##   @RequestMapping("/material")
   49      @ResponseBody
   50      public void InsertMaterial (HttpServletRequest request) throws ParseException {
   ..
   52      }
   53  
##   @RequestMapping("/repository")
   55      @ResponseBody
   56      public void newRepository (HttpServletRequest request) {
   ..
   58      }
   59  
##   @RequestMapping("/delete")
   61      @ResponseBody
   62      public void DeleteInRept (HttpServletRequest request) { inRepositoryImpl.DeleteInRept(request); }
   63  
##   @RequestMapping("/deleteDraft")
   65      @ResponseBody
   66      public void deleteDraft (HttpServletRequest request) { inRepositoryImpl.deleteDraft(request); }
   67  
##   @RequestMapping("/showinid")
   69      @ResponseBody
   70      public List showInId (HttpServletRequest request) {
   ..
   73      }
   74  
##   @RequestMapping("/check")
   76      @ResponseBody
   77      public String checkId (HttpServletRequest request) {
   ..
   80      }
   81  
##   @RequestMapping("/postCap")
   83      @ResponseBody
   84      public String postCap (HttpServletRequest request){
   ..
   87      }
   88  
##   @RequestMapping("/cgsh")
   90      @ResponseBody
   91      public List<Repository> cgsh () {
   ..
   93      }
   94  
##   @RequestMapping("/showmat")
   96      @ResponseBody
   97      public List<Repository> showmat (HttpServletRequest request) {
   ..
   99      }
  100  
##    @RequestMapping("/showproj")
  102      @ResponseBody
  103      public List<Repository> showproj (HttpServletRequest request) {
  ...
  105      }
  106  
##    @RequestMapping("/getInstId")
  108      @ResponseBody
  109      public String getInstId (String instid, HttpServletRequest request){
  ...
  111      }
  112  
##    @RequestMapping("/updateApprove")
  114      @ResponseBody
  115      public void updateApprove (String instId){
  ...
  117      }
  118  
##    @RequestMapping("/getApprove")
  120      @ResponseBody
  121      public void getApprove (HttpServletRequest request){
  ...
  123      }
  124  
##    @RequestMapping("/postInfo")
  126      @ResponseBody
  127      public List<Repository> postInfo (HttpServletRequest request){
  ...
  129      }
  130  
##    @RequestMapping("/node")
  132      @ResponseBody
  133      public String node (HttpServletRequest request){
  ...
  135      }
  136  
##    @RequestMapping("/approveName")
  138      @ResponseBody
  139      public List<Repository> approveName (HttpServletRequest request){
  ...
  141      }
  142  
##    @RequestMapping("/findDraft")
  144      @ResponseBody
  145      public PageInfo<Repository> showDraft (Page page) {
  ...
  148      }
  149  
##    @RequestMapping("/insertDraft")
  151      @ResponseBody
  152      public void insertDraft (HttpServletRequest request) throws ParseException{
  ...
  154      }
  155  
##    @RequestMapping("/checkDraft")
  157      @ResponseBody
  158      public String checkDraft (HttpServletRequest request) {
  ...
  160      }
  161  
##    @RequestMapping("/postDraft")
  163      @ResponseBody
  164      public List<Repository> postDraft (HttpServletRequest request){
  ...
  166      }
  167  
##    @RequestMapping("/getNotice")
  169      @ResponseBody
  170      public List<HashMap<String, Object>> getNotice(HttpServletRequest request) { return inRepositoryImpl.notice(request); }
  171  
##    @RequestMapping("/noticeChild")
  173      @ResponseBody
  174      public List<HashMap<String, Object>> noticeChild(HttpServletRequest request) { return inRepositoryImpl.noticeChild(request); }
  175  
##    @RequestMapping("/findNotice")
  177      @ResponseBody
  178      public PageInfo<Repository> showNotice (Page page, HttpServletRequest request) {

# backend\src\main\java\com\elex\oa\controller\eqptController\InventoryController.java:
   19  @Controller
   20  @CrossOrigin
##RequestMapping("/inv")
   22  public class InventoryController {
   23  
   ..
   25      private InventoryImpl inventoryImpl;
   26  
##   @RequestMapping("/find")
   28      @ResponseBody
   29      public PageInfo<Repository> showInfo(Page page) {
   ..
   32      }
   33  
##   @RequestMapping("/search")
   35      @ResponseBody
   36      public PageInfo<Repository> searchInfo(Page page,HttpServletRequest request) {
   ..
   39      }
   40  
##   @RequestMapping("/delete")
   42      @ResponseBody
   43      public void deleteInfo(HttpServletRequest request) {
   ..
   45      }
   46  
##   @RequestMapping("/deleteDraft")
   48      @ResponseBody
   49      public void deleteDraft(HttpServletRequest request) {
   ..
   51      }
   52  
##   @RequestMapping("/insert")
   54      @ResponseBody
   55      public void insertInfo(HttpServletRequest request) throws ParseException {
   ..
   57      }
   58  
##   @RequestMapping("/save")
   60      @ResponseBody
   61      public void saveInfo(HttpServletRequest request) throws ParseException {
   ..
   63      }
   64  
##   @RequestMapping("/checkDraft")
   66      @ResponseBody
   67      public String checkDraft(HttpServletRequest request) {
   ..
   69      }
   70  
##   @RequestMapping("/showinvid")
   72      @ResponseBody
   73      public List showInvId (HttpServletRequest request) {
   ..
   76      }
   77  
##   /*@RequestMapping("/chooseMat")
   79      @ResponseBody
   80      public List<Material> chooseMat(HttpServletRequest request){
   ..
   83      }*/
   84  
##   @RequestMapping("/matinrept")
   86      @ResponseBody
   87      public List<Material> matinrept(HttpServletRequest request){
   ..
   90      }
   91  
##   @RequestMapping("/reptlist")
   93      @ResponseBody
   94      public List<Repository> ReptList(){
   ..
   96      }
   97  
##   @RequestMapping("/openDraft")
   99      @ResponseBody
  100      public List<Repository> openDraft(HttpServletRequest request){
  ...
  102      }
  103  
##    @RequestMapping("/changeDraft")
  105      @ResponseBody
  106      public void changeDraft(HttpServletRequest request) throws ParseException{
  ...
  108      }
  109  
##    @RequestMapping("/getInvId")
  111      @ResponseBody
  112      public List getInvId(){
  ...
  114      }
  115  
##    @RequestMapping("/insertInv")
  117      @ResponseBody
  118      public List<HashMap<String, Object>> insertInv(String instid){

# backend\src\main\java\com\elex\oa\controller\eqptController\LinkmanController.java:
   16  @CrossOrigin
   17  @Controller
##RequestMapping("/linkman")
   19  public class LinkmanController {
   20  
   ..
   23  
   24      @ResponseBody
##   @RequestMapping("/list")
   26      public PageInfo<Linkman> LinkmanList(Page page){
   27          PageInfo<Linkman> listL = linkmanImpl.showLinkman(page);
   ..
   30  
   31      @ResponseBody
##   @RequestMapping("/new")
   33      public String newLinkMan(HttpServletRequest request){
   34          return linkmanImpl.newLinkman(request);
   ..
   36  
   37      @ResponseBody
##   @RequestMapping("/delete")
   39      public void  deleteLinkMan(HttpServletRequest request){
   40          linkmanImpl.deleteLinkman(request);
   ..
   42  
   43      @ResponseBody
##   @RequestMapping("/change")
   45      public void  changeLinkman(HttpServletRequest request){
   46          linkmanImpl.changeLinkman(request);
   ..
   49  
   50      @ResponseBody
##   @RequestMapping("search")
   52      public PageInfo<Linkman> searchLinkman(Page page,HttpServletRequest request){
   53          PageInfo<Linkman> listL = linkmanImpl.searchLinkman(page,request);

# backend\src\main\java\com\elex\oa\controller\eqptController\MaterialController.java:
   16  @CrossOrigin
   17  @Controller
##RequestMapping("/material")
   19  public class MaterialController {
   20  
   ..
   22      private MaterialImpl materialImpl;
   23  
##   @RequestMapping("/list")
   25      @ResponseBody
   26      public PageInfo<Material> materialList(Page page){
   ..
   29      }
   30  
##   @RequestMapping("/search")
   32      @ResponseBody
   33      public PageInfo<Material> materialSearch(Page page, HttpServletRequest request){
   ..
   36      }
   37  
##   @RequestMapping("/new")
   39      @ResponseBody
   40      public void InsertMaterial (Material material,HttpServletRequest request) {
   ..
   42      }
   43  
##   @RequestMapping("/change")
   45      @ResponseBody
   46      public Material materialChange(HttpServletRequest request){
   ..
   48      }
   49  
##   @RequestMapping("/save")
   51      @ResponseBody
   52      public void saveMaterial(HttpServletRequest request) {
   ..
   54      }
   55  
##   @RequestMapping("/delete")
   57      @ResponseBody
   58      public void deleteMaterial(HttpServletRequest request){
   ..
   60      }
   61  
##   @RequestMapping("/check")
   63      @ResponseBody
   64      public String checkId (HttpServletRequest request){
   ..
   66      }
   67  
##   @RequestMapping("/record")
   69      @ResponseBody
   70      public String record (HttpServletRequest request) {

# backend\src\main\java\com\elex\oa\controller\eqptController\MaterialMtController.java:
   17  @CrossOrigin
   18  @Controller
##RequestMapping("/materialD")
   20  public class MaterialMtController {
   21  
   ..
   23      private MaterialMtImpl materialMtImpl;
   24  
##   @RequestMapping("/list")
   26      @ResponseBody
   27      public PageInfo<Material> materialList(Page page){
   ..
   30      }
   31  
##   @RequestMapping("/listInRept")
   33      @ResponseBody
   34      public PageInfo<Material> materialInRept(Page page,HttpServletRequest request){
   ..
   37      }
   38  
##   @RequestMapping("/search")
   40      @ResponseBody
   41      public PageInfo<Material> materialSearch(Page page, HttpServletRequest request){
   ..
   44      }
   45  
##   @RequestMapping("/record")
   47      @ResponseBody
   48      public String record (HttpServletRequest request) {
   49          return materialMtImpl.record(request);
   50      }
##   /*@RequestMapping("/new")
   52      @ResponseBody
   53      public void InsertMaterial (Material material,HttpServletRequest request) throws ParseException {
   ..
   55      }
   56  
##   @RequestMapping("/change")
   58      @ResponseBody
   59      public Material materialChange(HttpServletRequest request){
   ..
   61      }
   62  
##   @RequestMapping("/save")
   64      @ResponseBody
   65      public void saveMaterial(HttpServletRequest request) throws ParseException{
   ..
   67      }
   68  
##   @RequestMapping("/delete")
   70      @ResponseBody
   71      public void deleteMaterial(HttpServletRequest request){
   ..
   75  
   76  
##   @RequestMapping("/state")
   78      @ResponseBody
   79      public String state (HttpServletRequest request) {

# backend\src\main\java\com\elex\oa\controller\eqptController\OutRepositoryController.java:
   19  @CrossOrigin
   20  @Controller
##RequestMapping("/out")
   22  public class OutRepositoryController {
   23  
   ..
   25      private OutRepositoryImpl outRepositoryImpl;
   26  
##   @RequestMapping("/find")
   28      @ResponseBody
   29      public PageInfo<Repository> ShowRepository (Page page, HttpServletRequest request){
   ..
   32      }
   33  
##   @RequestMapping("/new")
   35      @ResponseBody
   36      public String InsertNew (HttpServletRequest request) throws ParseException {
   ..
   38      }
   39  
##   @RequestMapping("/search")
   41      @ResponseBody
   42      public PageInfo<Repository> searchRepository(Page page, HttpServletRequest request){
   ..
   45      }
   46  
##   @RequestMapping("/repository")
   48      @ResponseBody
   49      public void OutRepository (HttpServletRequest request) {
   ..
   51      }
   52  
##   @RequestMapping("/material")
   54      @ResponseBody
   55      public void OutMaterial (HttpServletRequest request) throws ParseException  {
   ..
   58  
   59  
##   @RequestMapping("/showoutid")
   61      @ResponseBody
   62      public List showOutId (HttpServletRequest request) {
   ..
   65      }
   66  
##   @RequestMapping("/delete")
   68      @ResponseBody
   69      public void DeleteInRept (HttpServletRequest request) { outRepositoryImpl.DeleteOutRept(request); }
   70  
##   @RequestMapping("/deleteDraft")
   72      @ResponseBody
   73      public void deleteDraft (HttpServletRequest request) { outRepositoryImpl.deleteDraft(request); }
   74  
##   @RequestMapping("/check")
   76      @ResponseBody
   77      public String checkId (HttpServletRequest request) {
   ..
   80      }
   81  
##   @RequestMapping("/negative")
   83      @ResponseBody
   84      public String negative (HttpServletRequest request) {
   ..
   87      }
   88  
##   @RequestMapping("/rcly")
   90      @ResponseBody
   91      public List<Repository> rcly () {
   ..
   93      }
   94  
##   @RequestMapping("/xsfh")
   96      @ResponseBody
   97      public List<Repository> xsfh () {
   ..
   99      }
  100  
##    @RequestMapping("/cgth")
  102      @ResponseBody
  103      public List<Repository> cgth () {
  ...
  105      }
  106  
##    @RequestMapping("/showmatR")
  108      @ResponseBody
  109      public List<Repository> showmatR (HttpServletRequest request) {
  ...
  111      }
  112  
##    @RequestMapping("/showmatX")
  114      @ResponseBody
  115      public List<Repository> showmatX (HttpServletRequest request) {
  ...
  117      }
  118  
##    @RequestMapping("/showmatC")
  120      @ResponseBody
  121      public List<Repository> showmatC (HttpServletRequest request) {
  ...
  123      }
  124  
##    @RequestMapping("/showprojX")
  126      @ResponseBody
  127      public List<Repository> showprojX (HttpServletRequest request) {
  ...
  129      }
  130  
##    @RequestMapping("/showprojR")
  132      @ResponseBody
  133      public List<Repository> showprojR (HttpServletRequest request) {
  ...
  135      }
  136  
##    @RequestMapping("/getInstId")
  138      @ResponseBody
  139      public String getInstId (String instid){
  ...
  141      }
  142  
##    @RequestMapping("/updateApprove")
  144      @ResponseBody
  145      public void updateApprove (String instId){
  ...
  147      }
  148  
##    @RequestMapping("/getApprove")
  150      @ResponseBody
  151      public void getApprove (HttpServletRequest request){
  ...
  153      }
  154  
##    @RequestMapping("/node")
  156      @ResponseBody
  157      public String node (HttpServletRequest request){
  ...
  159      }
  160  
##    @RequestMapping("/postInfo")
  162      @ResponseBody
  163      public List<Repository> postInfo (HttpServletRequest request){
  ...
  165      }
  166  
##    @RequestMapping("/approveName")
  168      @ResponseBody
  169      public List<Repository> approveName (HttpServletRequest request){
  ...
  171      }
  172  
##    @RequestMapping("/findDraft")
  174      @ResponseBody
  175      public PageInfo<Repository> showDraft (Page page) {
  ...
  178      }
  179  
##    @RequestMapping("/insertDraft")
  181      @ResponseBody
  182      public void insertDraft (HttpServletRequest request) throws ParseException{
  ...
  184      }
  185  
##    @RequestMapping("/checkDraft")
  187      @ResponseBody
  188      public String checkDraft (HttpServletRequest request) {
  ...
  190      }
  191  
##    @RequestMapping("/postDraft")
  193      @ResponseBody
  194      public List<Repository> postDraft (HttpServletRequest request){
  ...
  196      }
  197  
##    @RequestMapping("/getNotice")
  199      @ResponseBody
  200      public List<HashMap<String, Object>> getNotice(HttpServletRequest request) { return outRepositoryImpl.notice(request); }
  201  
##    @RequestMapping("/noticeChild")
  203      @ResponseBody
  204      public List<HashMap<String, Object>> noticeChild(HttpServletRequest request) { return outRepositoryImpl.noticeChild(request); }
  205  
##    @RequestMapping("/findNotice")
  207      @ResponseBody
  208      public PageInfo<Repository> showNotice (Page page, HttpServletRequest request) {

# backend\src\main\java\com\elex\oa\controller\eqptController\PartnerController.java:
   17  @Controller
   18  @CrossOrigin
##RequestMapping("/partner")
   20  public class PartnerController {
   21  
   ..
   23      private PartnerImpl partnerImpl;
   24  
##   @RequestMapping("/list")
   26      @ResponseBody
   27      public PageInfo<Partner> partnerList(Page page){
   ..
   30      }
   31  
##   @RequestMapping("/getPart")
   33      @ResponseBody
   34      public List<Partner> getPart(){
   ..
   37      }
   38  
##   @RequestMapping("/search")
   40      @ResponseBody
   41      public PageInfo<Partner> searchPartner(Page page, HttpServletRequest request){
   ..
   44      }
   45  
##   @RequestMapping("/new")
   47      @ResponseBody
   48      public String InsertPartner (Partner partner,HttpServletRequest request){
   ..
   50      }
   51  
##   @RequestMapping("/name")
   53      @ResponseBody
   54      public Partner Name(HttpServletRequest request){
   ..
   57  
   58  
##   @RequestMapping("/change")
   60      @ResponseBody
   61      public void ChangePartner (Partner partner,HttpServletRequest request){
   ..
   63      }
   64  
##   @RequestMapping("/delete")
   66      @ResponseBody
   67      public void DeletePartner (Partner partner,HttpServletRequest request){
   ..
   69      }
   70  
##   @RequestMapping("/authorize")
   72      @ResponseBody
   73      public List<Linkman> authorize(){
   ..
   76      }
   77  
##   @RequestMapping("/authorizeId")
   79      @ResponseBody
   80      public List<Linkman> authorizeId(HttpServletRequest request){
   ..
   84  
   85  
##   @RequestMapping("/otherName")
   87      @ResponseBody
   88      public List<Linkman> otherName(HttpServletRequest request){
   ..
   91      }
   92  
##   @RequestMapping("/authorizeInfo")
   94      @ResponseBody
   95      public List<Linkman> authorizeInfo(HttpServletRequest request){

# backend\src\main\java\com\elex\oa\controller\eqptController\PositionController.java:
   15  import java.util.List;
   16  
##RequestMapping("/position")
   18  @Controller
   19  @CrossOrigin
   ..
   23      private PositionServiceImpl positionServiceimpl;
   24  
##   @RequestMapping("/list")
   26      @ResponseBody
   27      public PageInfo<Repository> positionList(Page page){
   ..
   31  
   32      @ResponseBody
##   @RequestMapping("/search")
   34      public PageInfo<Repository> searchPosition(Page page,HttpServletRequest request){
   35          PageInfo<Repository> listP = positionServiceimpl.searchPosition(page,request);
   ..
   38  
   39      @ResponseBody
##   @RequestMapping("/change")
   41      public void changePosition (Repository repository,HttpServletRequest request){
   42          positionServiceimpl.changePosition(repository,request);
   ..
   44  
   45      @ResponseBody
##   @RequestMapping("/new")
   47      public String insertPosiiton(Repository repository,HttpServletRequest request){
   48          return positionServiceimpl.insertPosition(repository,request);
   49      }
   50  
##   @RequestMapping("/delete")
   52      @ResponseBody
   53      public void deletePosition(Repository repository,HttpServletRequest request){
   ..
   55      }
   56  
##   @RequestMapping("/reptlist")
   58      @ResponseBody
   59      public List<Repository> ReptList(){
   ..
   61      }
   62  
##   @RequestMapping("/reptname")
   64      @ResponseBody
   65      public List<Repository> ReptName(HttpServletRequest request){
   ..
   67      }
   68  
##   @RequestMapping("/record")
   70      @ResponseBody
   71      public String record (HttpServletRequest request) {

# backend\src\main\java\com\elex\oa\controller\eqptController\RepositoryController.java:
   20  @Controller
   21  @CrossOrigin
##RequestMapping("/repository")
   23  public class RepositoryController {
   24  
   ..
   27  
   28  
##   @RequestMapping("/list")
   30      @ResponseBody
   31      public PageInfo<Repository> repositoryList(Page page){
   ..
   34      }
   35  
##   @RequestMapping("/search")
   37      @ResponseBody
   38      public PageInfo<Repository> searchRepository(Page page, HttpServletRequest request){
   ..
   41      }
   42  
##   @RequestMapping("/new")
   44      @ResponseBody
   45      public String InsertRepository (Repository repository,HttpServletRequest request){
   ..
   47      }
   48  
##   /*@RequestMapping("/position")
   50      @ResponseBody
   51      public Repository Position(HttpServletRequest request){
   ..
   54  
   55  
##   @RequestMapping("/change")
   57      @ResponseBody
   58      public void ChangeRepository (Repository repository,HttpServletRequest request){
   ..
   60      }
   61  
##   @RequestMapping("/delete")
   63      @ResponseBody
   64      public void DeleteRepository (Repository repository,HttpServletRequest request){
   ..
   66      }
   67  
##   @RequestMapping("/reptlist")
   69      @ResponseBody
   70      public List<Repository> ReptList(){
   ..
   72      }
   73  
##   @RequestMapping("/getPost")
   75      @ResponseBody
   76      public List<Repository> getPost(HttpServletRequest request){
   ..
   78      }
   79  
##   @RequestMapping("/postlist")
   81      @ResponseBody
   82      public List<Repository> PostList(){
   ..
   84      }
   85  
##   @RequestMapping("/matlist")
   87      @ResponseBody
   88      public List<Material> MatList(){
   ..
   90      }
   91  
##   @RequestMapping("/getUser")
   93      @ResponseBody
   94      public List<User> getUser() {
   ..
   97      }
   98  
##   @RequestMapping("/matOutRept")
  100      @ResponseBody
  101      public List<Repository> matOutRept(HttpServletRequest request){
  ...
  104      }
  105  
##    @RequestMapping("/matOutPost")
  107      @ResponseBody
  108      public List<Repository> matOutPost(HttpServletRequest request){
  ...
  111      }
  112  
##    @RequestMapping("/matInRept")
  114      @ResponseBody
  115      public List<Repository> matInRept(HttpServletRequest request){
  ...
  118      }
  119  
##    @RequestMapping("/matInPost")
  121      @ResponseBody
  122      public List<Repository> matInPost(HttpServletRequest request){
  ...
  125      }
  126  
##    @RequestMapping("/record")
  128      @ResponseBody
  129      public String record (HttpServletRequest request) {
  ...
  131      }
  132  
##    @RequestMapping("/checkBS")
  134      @ResponseBody
  135      public String checkBS(HttpServletRequest request){
  ...
  137      }
  138  
##    @RequestMapping("/getCate")
  140      @ResponseBody
  141      public List<HashMap<String, Object>> getCate(){
  ...
  143      }
  144  
##    @RequestMapping("/checkCate")
  146      @ResponseBody
  147      public List<HashMap<String, Object>> checkCate(HttpServletRequest request){
  ...
  149      }
  150  
##    @RequestMapping("/insertCate")
  152      @ResponseBody
  153      public void insertCate(HttpServletRequest request){
  ...
  155      }
  156  
##    @RequestMapping("/deleteCate")
  158      @ResponseBody
  159      public void deleteCate(HttpServletRequest request){
  ...
  161      }
  162  
##    /*@RequestMapping("/matInRept")
  164      @ResponseBody
  165      public List<Repository> matInRept(HttpServletRequest request){
  ...
  167      }
  168  
##    @RequestMapping("/matInPost")
  170      @ResponseBody
  171      public List<Repository> matInPost(HttpServletRequest request){
  ...
  173      }
  174  
##    @RequestMapping("/matOutRept")
  176      @ResponseBody
  177      public Map matOutRept(HttpServletRequest request){
  ...
  179      }
  180  
##    @RequestMapping("/matOutPost")
  182      @ResponseBody
  183      public List<Repository> matOutPost(HttpServletRequest request){

# backend\src\main\java\com\elex\oa\controller\eqptController\RepositoryMtController.java:
   19  @CrossOrigin
   20  @Controller
##RequestMapping("/repositorymt")
   22  public class RepositoryMtController {
   23  
   ..
   25      private RepositoryMtImpl repositoryMtImpl;
   26  
##   @RequestMapping("/list")
   28      @ResponseBody
   29      public PageInfo<Repository> repositoryList(Page page) {
   ..
   32      }
   33  
##   @RequestMapping("/search")
   35      @ResponseBody
   36      public PageInfo<Repository> repositorySearch(Page page, HttpServletRequest request) {
   ..
   39      }
   40  
##   @RequestMapping("/new")
   42      @ResponseBody
   43      public void insertRepositoryMt (HttpServletRequest request){
   ..
   45      }
   46  
##   @RequestMapping("/delete")
   48      @ResponseBody
   49      public void deleteRepositoryMt(HttpServletRequest request){
   ..
   51      }
   52  
##   @RequestMapping("/save")
   54      @ResponseBody
   55      public void changeRepositoryMt(HttpServletRequest request){
   ..
   57      }
   58  
##   @RequestMapping("/matlist")
   60      @ResponseBody
   61      public List<Material> ReptList(){
   ..
   63      }
   64  
##   @RequestMapping("/updFix")
   66      @ResponseBody
   67      public void updFix(HttpServletRequest request) {
   ..
   69      }
   70  
##   @RequestMapping("/updOtherFix")
   72      @ResponseBody
   73      public void updOtherFix(HttpServletRequest request) {
   ..
   75      }
   76  
##   @RequestMapping("/getUser")
   78      @ResponseBody
   79      public List<User> getUser() {

# backend\src\main\java\com\elex\oa\controller\eqptController\ShiftRepositoryController.java:
   19  @CrossOrigin
   20  @Controller
##RequestMapping("/shift")
   22  public class ShiftRepositoryController {
   23  
   ..
   25      private ShiftRepositoryImpl shiftRepositoryImpl;
   26  
##   @RequestMapping("/find")
   28      @ResponseBody
   29      public PageInfo<Repository> showrepository(Page page, HttpServletRequest request){
   ..
   32      }
   33  
##   @RequestMapping("/search")
   35      @ResponseBody
   36      public PageInfo<Repository> searchShift(Page page, HttpServletRequest request){
   ..
   39      }
   40  
##   @RequestMapping("/new")
   42      @ResponseBody
   43      public String newRepository(HttpServletRequest request) throws ParseException{
   ..
   45      }
   46  
##   @RequestMapping("/repository")
   48      @ResponseBody
   49      public void changeRepository(HttpServletRequest request){
   ..
   51      }
   52  
##   @RequestMapping("/showshiftid")
   54      @ResponseBody
   55      public List showShiftId (HttpServletRequest request) {
   ..
   58      }
   59  
##   @RequestMapping("/postCap")
   61      @ResponseBody
   62      public String postCap(HttpServletRequest request){
   ..
   65      }
   66  
##   @RequestMapping("/canIn")
   68      @ResponseBody
   69      public String canIn(HttpServletRequest request){
   ..
   72      }
   73  
##   @RequestMapping("/canOut")
   75      @ResponseBody
   76      public String canOut(HttpServletRequest request){
   ..
   79      }
   80  
##   @RequestMapping("/jy")
   82      @ResponseBody
   83      public List<Repository> jy () {
   ..
   85      }
   86  
##   @RequestMapping("/scll")
   88      @ResponseBody
   89      public List<Repository> scll () {
   ..
   91      }
   92  
##   @RequestMapping("/gh")
   94      @ResponseBody
   95      public List<Repository> gh () {
   ..
   97      }
   98  
##   @RequestMapping("/sctl")
  100      @ResponseBody
  101      public List<Repository> sctl () {
  ...
  103      }
  104  
##    @RequestMapping("/showmatJ")
  106      @ResponseBody
  107      public List<Repository> showmatJ (HttpServletRequest request) {
  ...
  109      }
  110  
##    @RequestMapping("/showmatL")
  112      @ResponseBody
  113      public List<Repository> showmatL (HttpServletRequest request) {
  ...
  115      }
  116  
##    @RequestMapping("/showmatG")
  118      @ResponseBody
  119      public List<Repository> showmatG (HttpServletRequest request) {
  ...
  121      }
  122  
##    @RequestMapping("/showmatT")
  124      @ResponseBody
  125      public List<Repository> showmatT (HttpServletRequest request) {
  ...
  127      }
  128  
##    @RequestMapping("/showprojJ")
  130      @ResponseBody
  131      public List<Repository> showprojJ (HttpServletRequest request) {
  ...
  133      }
  134  
##    @RequestMapping("/showprojL")
  136      @ResponseBody
  137      public List<Repository> showprojL (HttpServletRequest request) {
  ...
  139      }
  140  
##    @RequestMapping("/getInstId")
  142      @ResponseBody
  143      public String getInstId (String instid){
  ...
  145      }
  146  
##    @RequestMapping("/updateApprove")
  148      @ResponseBody
  149      public void updateApprove (String instId){
  ...
  151      }
  152  
##    @RequestMapping("/getApprove")
  154      @ResponseBody
  155      public void getApprove (HttpServletRequest request){
  ...
  157      }
  158  
##    @RequestMapping("/postInfo")
  160      @ResponseBody
  161      public List<Repository> postInfo (HttpServletRequest request){
  ...
  163      }
  164  
##    @RequestMapping("/node")
  166      @ResponseBody
  167      public String node (HttpServletRequest request){
  ...
  169      }
  170  
##    @RequestMapping("/approveName")
  172      @ResponseBody
  173      public List<Repository> approveName (HttpServletRequest request){
  ...
  175      }
  176  
##    @RequestMapping("/findDraft")
  178      @ResponseBody
  179      public PageInfo<Repository> showDraft (Page page) {
  ...
  182      }
  183  
##    @RequestMapping("/insertDraft")
  185      @ResponseBody
  186      public void insertDraft (HttpServletRequest request) throws ParseException{
  ...
  188      }
  189  
##    @RequestMapping("/checkDraft")
  191      @ResponseBody
  192      public String checkDraft (HttpServletRequest request) {
  ...
  194      }
  195  
##    @RequestMapping("/postDraft")
  197      @ResponseBody
  198      public List<Repository> postDraft (HttpServletRequest request){
  ...
  200      }
  201  
##    @RequestMapping("/getNotice")
  203      @ResponseBody
  204      public List<HashMap<String, Object>> getNotice(HttpServletRequest request) { return shiftRepositoryImpl.notice(request); }
  205  
##    @RequestMapping("/noticeChild")
  207      @ResponseBody
  208      public List<HashMap<String, Object>> noticeChild(HttpServletRequest request) { return shiftRepositoryImpl.noticeChild(request); }
  209  
##    @RequestMapping("/findNotice")
  211      @ResponseBody
  212      public PageInfo<Repository> showNotice (Page page, HttpServletRequest request) {
  ...
  215      }
  216  
##    @RequestMapping("/deleteDraft")
  218      @ResponseBody
  219      public void deleteDraft (HttpServletRequest request) { shiftRepositoryImpl.deleteDraft(request); }

195 matches across 12 files


##hing 2 files for "@RequestMapping("

# backend\src\main\java\com\elex\oa\controller\business\ClueController.java:
   33   * @Version 1.0
   34   **/
##RequestMapping("/clue")
   36  @Controller
   37  @CrossOrigin
   ..
   42      IOpportunityService iOpportunityService;
   43  
##   @RequestMapping("/getPageInfo")
   45      @ResponseBody
   46      public PageInfo<Clue> getPageInfo(
   ..
   52      }
   53  
##   @RequestMapping(value = "/clue_ADD",consumes = "multipart/form-data")
   55      @ResponseBody
   56      public Object clue_ADD(
   ..
   73      }
   74  
##   @RequestMapping("/getDetailClueinfo")
   76      @ResponseBody
   77      public Clue getDetailClueinfo(
   ..
   82      }
   83  
##   @RequestMapping("/getDetailOpportunityinfo")
   85      @ResponseBody
   86      public Opportunity getDetailOpportunityinfo(
   ..
   91      }
   92  
##   @RequestMapping(value = "/clue_UPDATE",consumes = "multipart/form-data")
   94      @ResponseBody
   95      public Object clue_UPDATE(
   ..
  107      }
  108  
##    @RequestMapping("/closeClueinfo")
  110      @ResponseBody
  111      public Object closeClueinfo(

# backend\src\main\java\com\elex\oa\controller\business\OpportunityController.java:
   32  @Controller
   33  @CrossOrigin
##RequestMapping("/opportunity")
   35  public class OpportunityController {
   36      @Autowired
   37      IOpportunityService iOpportunityService;
   38  
##   @RequestMapping("/opportunity_ADD")
   40      @ResponseBody
   41      public Object opportunity_ADD(
   ..
   57      }
   58  
##   @RequestMapping("/getPageInfo")
   60      @ResponseBody
   61      public PageInfo<Opportunity> getPageInfo(
   ..
   67      }
   68  
##   @RequestMapping("/getDetailOpportunityinfo")
   70      @ResponseBody
   71      public Opportunity getDetailOpportunityinfo(
   ..
   76      }
   77  
##   @RequestMapping(value = "/opportunity_UPDATE",consumes = "multipart/form-data")
   79      @ResponseBody
   80      public Object opportunity_UPDATE(
   ..
   92      }
   93  
##   @RequestMapping("/closeOpportunityinfo")
   95      @ResponseBody
   96      public Object closeOpportunityinfo(
   ..
  101      }
  102  
##    @RequestMapping("/getBusinessInfoByState_OFF")
  104      @ResponseBody
  105      public Map<String,Object> getBusinessInfoByState_OFF(){

14 matches across 2 files
