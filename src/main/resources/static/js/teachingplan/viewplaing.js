/**
 * Created by 汪冉冉 on 2017/4/28.
 */
var year=new Date().getFullYear();//获取当前年份
var month=new Date().getMonth()+1;//获取当前月份
var app4 = new Vue({
    el: '#MyPlaning',
    data: {
        url:"/TeachingPlan",
        planlists: [],//班级信息
        clickupdatebook:false,//是否显现和修改教材相关
        searchData:'',//搜索内容
        deleteitem:{},
        edititem:{   coursename:null,
            semester:null,
            gradeinfo:{ },
            bookinfo:{},
            planid:null,
            gradeid:null,
            bookid:null},
        chosebook:{},
        Allcolleges:[],//所有学院信息
        recomendprofessionlists:[],//推荐该在学院已有的专业
        Allgrade:[],//所有年级
        Allsemester:[],//某专业所有学期
        searchcampus: '',
        searchCollege: '',
        searchProfession: '',
        searchGrade:'',
        searchSemester: '',
        mybookswhenadd:[],//添加教学计划时的教材列表
        mybookswhenupdate:[],//修改教学计划时的教材列表
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
    computed: {
        filteredplan: function () {
            var self = this;
            return self.planlists.filter(function (item) {
                return (item.coursename.indexOf(self.searchData) !== -1)||
                    (item.gradeinfo.college.indexOf(self.searchData) !== -1)||
                    (item.gradeinfo.profession.indexOf(self.searchData) !== -1)||
                    (item.gradeinfo.grade==self.searchData)||
                    (item.gradeinfo.campus.indexOf(self.searchData) !== -1)||
                    (item.bookinfo.bookname.indexOf(self.searchData) !== -1)||
                    (item.bookinfo.author.indexOf(self.searchData) !== -1)||
                    (item.bookinfo.publishunit.indexOf(self.searchData) !== -1)||
                    (item.bookinfo.isbn.indexOf(self.searchData) !== -1)||
                    (item.bookinfo.supplier.indexOf(self.searchData) !== -1)||
                    (item.semester==self.searchData)||
                    (item.bookinfo.publishunit.indexOf(self.searchData) !== -1)
            })
        },

    },
    watch: {
        'searchCollege': function (newCollege) {//当searchCollege有变化时加载相关专业
            var _this = this;
            this.getrecomendprofessionlists(_this.searchCollege);},
        'searchProfession':function(){//当专业发生变化时加载它的学期
            var _this = this;
            this.getrecomendsemesterlists(_this.searchProfession);
        },
        'edititem.planid':function(newedititem){
            this.clickupdatebook=false;
        }

    },

    mounted:function(){
        this.$nextTick(function(){
            this.getList();
        });
    },

    methods:{
        getitemdelete:function(url,deleteitem){//删除
            var _this=this;
            axios.get(url, {
                params: {
                    planid:deleteitem.planid,
                    bookid:deleteitem.bookid,
                    coursename:deleteitem.coursename,
                    semester:deleteitem.semester,
                    gradeid:deleteitem.gradeid,
                     }
            }).then(
                function(response){
                    var r=response.data;
                    if(r>0){
                        var index = _this.planlists.indexOf(deleteitem);
                        _this.planlists.splice(index,1);//
                        //    alert("删除成功");
                    }
                    else{
                        alert("删除失败");
                    }
                },
                function(response){
                    alert("连接服务器失败");
                });
        },
        updateplan:function(editeitem){//修改教学计划信息
            var _this=this;
            alert("开始修改教学计划");

            axios.get("/TeachingPlan/updateplanByid",{
                params:{
                    planid:editeitem.planid,
                    bookid:editeitem.bookid,
                    coursename:editeitem.coursename,
                    semester:editeitem.semester,
                    gradeid:editeitem.gradeid,
                }}).then(
                function(response){//成功的回调
                    var result=response.data;
                    if(result<0){
                        alert("修改失败");
                        return;
                    }
                    else{
                        alert("修改成功");
                    editeitem.bookinfo=_this.chosebook;
                        _this.planlists.forEach(function (item,key) {
                            if(item.planid==editeitem.planid){
                                _this.set(_this.planlists,key,editeitem);
                                //    alert(item+"****"+key);
                            }
                        });}

                },
                function(response){//失败
                    alert("无法连接到服务器");
                }
            );
        },

        additem:function(){//添加教学计划
            var _this = this;
          //  alert("开始添加"+_this.searchcampus+_this.searchCollege+_this.searchGrade+_this.searchProfession+_this.additem.bookid+_this.additem.coursename+_this.additem.semester);
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
                var r= response.data;
                if(r!=null){//添加成功
                  alert("添加成功");
                    _this.planlists.push(r);//添加成功把添加项加到展示列表
                    _this.searchcampus=null;
                    _this.searchCollege=null;
                    _this.searchGrade=null;
                    _this.searchProfession=null;


                }
                 else {
                    alert("添加失败！")
                }
            },function(response){//连接服务器失败
                alert("连接服务器失败，请重新尝试");
            })
        },

        getbooks:function(booknameClue,type){//找到相关教材,type=1表示添加时选书，type=2表示修改时选书
            var _this = this;
            axios.get("/BookinfoContrller/findbookBybooknmae",{
                params: {
                    bookname:booknameClue
                }}).then(
                function(response){

                    if(type==1){ _this.mybookswhenadd=response.data;}
                    if(type==2){_this.mybookswhenupdate=response.data;}
                },
                function(response){
                    alert("无法连接到服务器");
                }
            );

        },

        query:function( searchcampus, searchCollege,searchProfession, searchGrade, searchSemester){//精确查询
            var _this = this;
            axios.get("/TeachingPlan/SearchPlanByinput",{
                params: {
                college:searchCollege,//学院

           grade:searchGrade,//年级

          profession:searchProfession,//专业

            campus:searchcampus,//校区

            semester:searchSemester,
                    typegrade:2
        }}
            ).then(
                function(response){
                _this.planlists=response.data;


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
        getCollegeList:function (){//点精确查询加载所有的学院
            var _this = this;
            _this.searchData="";
            axios.get("/GradeinfoContrller/getAllCollege").then(function(response){
                _this.Allcolleges=response.data;
                //  alert("学院成功"+ _this.Allcolleges);
            },function(errormessage){
                alert("失败"+ +errormessage);
            });

        },
        getList: function () {
            var _this = this;
            axios.get("/TeachingPlan/findAllPlan").then(function(response){
                _this.planlists=response.data;
            },function(errormessage){
                // alert("失败"+ +errormessage);
            });
        }
    }
})
