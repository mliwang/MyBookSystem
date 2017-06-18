/**
 * Created by 汪冉冉 on 2017/4/28.
 */
var year=new Date().getFullYear();//获取当前年份
var month=new Date().getMonth()+1;//获取当前月份
var app4 = new Vue({
    el: '#MyPlaning',
    data: {
        /*顶部相关*/
        Allcolleges:[],//所有学院信息
        recomendprofessionlists:[],//推荐该在学院已有的专业
        Allgrade:[],//该专业所有可能在校的年级
        Allsemester:[],//某专业所有学期
        searchcampus: '',
        searchCollege: '',
        searchProfession: '',
        searchGrade:'',
        searchSemester: '',

        /*左侧相关*/
        leftsearch:'',//左侧搜索
        planlists: [],//该专业所有教学计划
        planlists1:[],//该专业所有教学计划备份
        leftchoseplan:[],//左侧所选计划


        /*右侧相关*/
        semplanlists: [],//该专业某学期教学计划
        rightchoseplan:[],//右侧所选计划
        mybookswhenadd:[],//添加教学计划时的教材列表
        booknameClue:'',
        additem:{
            coursename:null,
            semester:null,
            gradeinfo:{
            },
            bookinfo:{
            },
            planid:null,
            gradeid:null,
            bookid:null
        }

    },

    watch: {
      'searchCollege': function (newCollege) {//当searchCollege有变化时加载相关专业
            var _this = this;
            this.getrecomendprofessionlists(_this.searchCollege);},
        'searchProfession':function(newProfession){//当专业发生变化时加载它的学期,该专业所有课程
            var _this = this;
            _this.Allgrade=[];
            _this.Allsemester=[];
            this.getrecomendsemesterlists(_this.searchProfession);

        },
        'searchGrade':function(newProfession){//当专业发生变化时加载它的学期,该专业所有课程
            var _this = this;

            this.query(_this.searchcampus, _this.searchCollege,_this.searchProfession,newProfession, null,1,2);
        },
        'searchSemester':function(){
            var _this = this;
            this.query(_this.searchcampus, _this.searchCollege,_this.searchProfession,_this.searchGrade, _this.searchSemester,2,2);
        },


    },
    mounted:function(){
        this.$nextTick(function(){
            this.getCollegeList();
        });
    },
    methods:{
        leftfuzzyquery:function(clue){//左侧模糊查询
            var self = this;

           var lists= self.planlists1.filter(function (item) {
                return (item.coursename.indexOf(clue) !== -1)||
                 (item.bookinfo.bookname.indexOf(clue) !== -1)||
                 (item.bookinfo.author.indexOf(clue) !== -1)||
                 (item.bookinfo.publishunit.indexOf(clue) !== -1)||
                 (item.bookinfo.isbn.indexOf(clue) !== -1)||
                 (item.bookinfo.supplier.indexOf(clue) !== -1)||
                 (item.bookinfo.publishunit.indexOf(clue) !== -1)
            });
            this.planlists=lists;

        },
        getbooks:function(booknameClue,type){//找到相关教材,type=1表示添加时选书，type=2表示修改时选书
            var _this = this;
            axios.get("/BookinfoContrller/findbookBybooknmae",{
                params: {
                    bookname:booknameClue
                }}).then(
                function(response){

                    if(type==1){ _this.mybookswhenadd=response.data;}
                    //if(type==2){_this.mybookswhenupdate=response.data;}
                },
                function(response){
                    alert("无法连接到服务器");
                }
            );
        },
        initialize:function(searchcampus,searchCollege,searchProfession,searchGrade,searchSemester){//初始化additem  alert("好"+searchcampus+searchCollege+searchProfession+searchSemester);
            if (searchcampus==null||searchCollege==null||searchProfession==null||searchGrade==null){
                alert("校区、学院、专业、年级、学期不能为空");
                return;
            }
            this.additem.semester=searchSemester;
            var _this=this;
            axios.get('/GradeinfoContrller/getfindGrade',{
                params: {
                    college:searchCollege,
                    grade: searchGrade,
                    profession:searchProfession,
                    campus:searchcampus
                }
            }).then(
                function(response){//请求成功
                    var getGrade=response.data;
                    if(getGrade==null){
                        alert("请先添加该年级");
                        return;
                    }
                    console.log("请求成功·"+getGrade);
                    _this.additem.gradeinfo=getGrade;
                    _this.additem.gradeid=getGrade.gradeid;
                },
                function(response){//请求失败
                    alert("服务器异常，请刷新后重新操作");
                });


        },
        additemtoright:function(){//添加其它教学计划到本学期
            var _this = this;
          //  alert(_this.additem);
            if (_this.semplanlists.indexOf(_this.additem)==-1){//原集合中不存在某计划
                axios.get("/TeachingPlan/Addnewplan",{
                    params:{
                        campus:_this.searchcampus,
                        college:_this.searchCollege,
                        grade:_this.searchGrade,
                        profession:_this.searchProfession,
                        bookid:_this.additem.bookid,
                        coursename:_this.additem.coursename,
                        semester:_this.additem.semester
                    }
                }).then(function(response){//连接服务器成功
                    var r=response.data;
                   // console.log();
                    if(r!=null) {//添加成功
                        alert("添加成功"+r);
                        _this.semplanlists.push(r);//添加成功把添加项加到展示列表
                        _this.planlists1.push(r);
                    }
                     },function(response){ alert("连接服务器失败，请重新尝试");});


            }
            else {
                alert("该计划已添加");
            }

        },
      /*  savesemplans:function(plans){//添加教学计划 //plans中带的gradeinfo仍然上一届的，交给后台后台需要统一处理

            var _this = this;
            /!*    $.ajax({
                type: "post",
                url: "/NewstudentPlan/AddnewstudentPlan",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify(plans),
                success: function (jsonResult) {
                    alert(jsonResult);
                }
            });*!/

            // alert( plans);
            axios.post("/NewstudentPlan/AddnewstudentPlan",plans).then(function(response){//连接服务器成功
                var r= response.data;
                if (r==-1){
                    alert("添加失败，当前教学计划中包含重复添加的教学计划");
                }
                else if(r==0){//插入为空
                     }
                else if(r==1){//之前已经插入
                    alert("该学期教学计划已经添加成功如要修改请前往”查看教学计划“模块修改");
                }
                else if(r==1000) {//首次添加成功

                    alert("添加成功");}
                else {}


            },function(response){//连接服务器失败
                alert("连接服务器失败，请重新尝试");
            })
        },*/

        deleterightchose:function(){//删除右侧所选
            var _this = this;
            _this.rightchoseplan.forEach(function (item) {
                axios.get("/TeachingPlan/deleteplanByid", {
                    params: {
                        planid:item.planid,
                        bookid:item.bookid,
                        coursename:item.coursename,
                        semester:item.semester,
                        gradeid:item.gradeid,
                    }
                }).then(
                    function(response){
                        var r=response.data;
                        if(r>0){
                            var index = _this.semplanlists.indexOf(item);
                            _this.semplanlists.splice(index,1);
                            //    alert("删除成功");
                        }
                        else{
                           // alert("删除失败");
                        }
                    },
                    function(response){
                        alert("连接服务器失败");
                    });


            });
        },
        movetoright:function(){//添加所选到右侧
            var _this = this;
           // alert( _this.leftchoseplan);
            _this.leftchoseplan.forEach(function (item) {
                if (_this.semplanlists.indexOf(item)==-1){//原集合中不存在某计划
                   if(_this.searchSemester!=item.semester){
                       item.semester=_this.searchSemester;//统一学期
                       axios.get("/TeachingPlan/updateplanByid",{
                           params:{
                               planid:item.planid,
                               bookid:item.bookid,
                               coursename:item.coursename,
                               semester:item.semester,
                               gradeid:item.gradeid

                           }}).then(function(response){
                               var result=response.data;
                               if(result<0){
                                   alert("修改失败");
                                   return;
                               }
                               else{
                                 //  alert("修改成功");
                                   _this.semplanlists.push(item);
                                  }

                           },
                           function(response){ alert("服务器异常");});
                   }

                }
            });
          //  alert( _this.semplanlists);
        },
        resetchose:function(searchcampus, searchCollege,searchProfession, searchGrade, searchSemester){//重置某一学期的列表
            alert(searchcampus+ searchCollege+searchProfession+ searchGrade+searchSemester+'');
            if (searchcampus==''||searchCollege==''||searchProfession==''||searchGrade==''||searchSemester==''){
                alert("校区、学院、专业、年级、学期都不能空！");
                return;
            }
            var _this = this;
            axios.get("/PlaitPlaningContrller/resetplan",{
                params: {
                    college:searchCollege,//学院
                    grade:searchGrade,//年级
                    profession:searchProfession,//专业
                    campus:searchcampus,//校区
                    semester:searchSemester//学期，
                           }}
            ).then(
                function(response){
                    var r=response.data;
             if (r){
                 _this.semplanlists.splice(0, _this.semplanlists.length); //删除从指定位置deletePos开始的指定数量deleteCount的元素，数组形式返回所移除的元素
                }
                },function(response){ alert("服务器异常");});


        },
           query:function( searchcampus, searchCollege,searchProfession, searchGrade, searchSemester,type,typegrade){//精确查询,typegrade=1表示有当前年级的就查当前年级，没有当前年级就查上一年级
               if (searchcampus==null||searchCollege==null||searchProfession==null||searchGrade==null){
                   alert("校区或学院或专业或年级不能为空");
               }
               else if (type==2&&searchSemester==null){alert("学期不能为空！")}
               console.log("开始查询"+type);

            var _this = this;
            axios.get("/TeachingPlan/SearchPlanByinput",{
                params: {
                college:searchCollege,//学院
           grade:searchGrade,//年级
          profession:searchProfession,//专业
            campus:searchcampus,//校区
            semester:searchSemester,//学期，
                    typegrade:typegrade//typegrade=1表示有当前年级的就查当前年级，没有当前年级就查上一年级,为2代表就查当前年级
                }}
            ).then(
                function(response){
                    if (type==1){//查该专业所有的教学计划
                       _this.planlists=response.data;
                        _this.planlists1=_this.planlists;
                        console.log("查询结果"+response);
                            }
                    else if (type==2){//查某一学期的教学计划
                        _this.semplanlists=response.data;

                    }
                    else
                    {

                    }
            },
                function(response){
           alert("服务器异常");
            });

        },
        getrecomendsemesterlists:function(searchProfession){//根据专业找到对应年级和几年制
            var _this = this;
            if(searchProfession !=null){
                axios.get("/GradeinfoContrller/getschoolSystemByProfession",{  params: {profession:searchProfession}}).then(function(response){
                    var schoolsystem =response.data;//几年制
                 //   console.log("得到几年制"+schoolsystem);
                    if (month>9){
                        for (var i=0;i<schoolsystem;i++) {
                            _this.Allgrade.push(year - i);//获取该专业年级
                        }
                    }
                    else
                    {    for (var i=1;i<=schoolsystem;i++){
                        _this.Allgrade.push(year-i);//获取该专业年级
                    }
                    }
                    for (var i=1;i<=schoolsystem*2;i++)
                    { _this.Allsemester.push(i);}
                    //  alert("成功加载相关专业");
                });
            }

        },
        getrecomendprofessionlists:function(newcollege){//找到某学院对应专业
            var _this = this;
            if(newcollege !=null){

                axios.get("/GradeinfoContrller/getProfessionByCollege",{  params: {college:newcollege}}).then(function(response){
                    _this.recomendprofessionlists=response.data;
                    //  alert("成功加载相关专业");
                });

            }
        },
        getCollegeList:function (){//加载所有的学院
            var _this = this;

            axios.get("/GradeinfoContrller/getAllCollege").then(function(response){
                _this.Allcolleges=response.data;
                //  alert("学院成功"+ _this.Allcolleges);
            },function(errormessage){
                alert("失败"+ +errormessage);
            });

        },

    }
})
