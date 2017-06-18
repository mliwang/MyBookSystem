/**
 * Created by Administrator on 2017/3/14.
 */
function addtoogle (){
    $('#modal-container-521717').modal('toogle');
};
var year=new Date().getFullYear();//获取当前年份
var app4 = new Vue({
    el: '#Myclassinfo',
    data: {
        url:"/ClassinfoContrller",
       classinfolists: [],//班级信息
        searchData:'',//搜索内容
        Allcolleges:[],//所有学院信息
        recomendprofessionlists:[],//推荐该在学院已有的专业
        classnamelists:[],
        AllschoolSystem:[4,5,7],
        deleteitem:{},
        edititem:{},
        addtip:false,
       // AllProfession:[],//所有专业
        Allclassname:[],
        Allgrade:[year,year-1,year-2,year-3,year-4,year-5],//所有年级
        Allcampus:["黄","昙"],
        addclassinfo:{//添加班级信息
            classid:null,
                college:null,
                grade:null,
                profession:null,
                campus:null,

            gradeid:null,
            classname:null,
            numofclassstu:null,
            numoforderbookstu:null,
            schoolSystem:null,
            numofclass:null
        }

    },

    mounted:function(){
        this.$nextTick(function(){
            this.getList();
        });
    },
    computed: {
  classnamelists:function(){
            return [this.addclassinfo.profession+"一班",this.addclassinfo.profession+"二班",this.addclassinfo.profession+"三班",
                this.addclassinfo.profession+"四班", this.addclassinfo.profession+"五班", this.addclassinfo.profession+"六班"];
        },
             filteredgradeinfo: function () {
            var self = this;
            return self.classinfolists.filter(function (user) {
                return (user.college.indexOf(self.searchData) !== -1)||
                    (user.profession.indexOf(self.searchData) !== -1)||
                    (user.grade==self.searchData)||
                    (user.campus.indexOf(self.searchData) !== -1)
            })
        }
    },
    watch: {
    'addclassinfo.college': function (newCollege) {//当addclassinfo.college有变化时加载相关专业
        var _this = this;
       this.getrecomendprofessionlists(_this.addclassinfo.college);
   },

    },
    methods:{
      /*  getAddItemSave:function(){//保存所填加班级会快速执行
            var _this = this;
            var k=_this.addclassinfo;
            this.checkgrade(k);
            var gradeid=_this.addclassinfo.gradeid;
            if(gradeid!=null&&gradeid>0){//当前年级存在则添加班级
                _this.addtip="您刚刚所添加的年级存在，添加相关班级gradeid";
                _this.addnewclassinfo(_this.addclassinfo);
            }else if(gradeid<0){//当前年级不存在
                _this.addtip="您刚刚所添加的班级对应的年级不存在，请重新添加或先前往-> ”年级基本信息“ 添加相关年级";
                return;
            }
        },*/
      /*  checkgrade:function(addclassinfo){//检查年级当前所写年级是否存在
            var _this = this;
            alert(addclassinfo.college+addclassinfo.grade+addclassinfo.profession+addclassinfo.classname);
            //查看所添加班级所属年级是否已经存在，为添加的班级设置gradeId,如果不存在询问该年级是几年制

            axios.get('/getfindGrade',{
                params: {
                college:addclassinfo.college,
                grade: addclassinfo.grade,
                profession: addclassinfo.profession,
                campus:addclassinfo.campus
            }
            }).then(
                function(response){//请求成功
                    var getGradeid=response.data;
                    console.log("请求成功·"+getGradeid);
                    _this.addclassinfo.gradeid=getGradeid;
                },
                function(response){//请求失败
                    alert("服务器异常，请刷新后重新操作");
                });

        },*/
        addnewclassinfo:function(addclassinfo){//再添加班级
       /*  alert(addclassinfo.college+addclassinfo.grade+addclassinfo.profession+addclassinfo.classname+addclassinfo.gradeid);*/
            console.log("开始添加");
            var _this = this;
            var classinfopar=addclassinfo;
            axios.get( _this.url+"/savenewClassinfo",{
                params:{
                    classid:null,
                    classname: classinfopar.classname,
                    numofclassstu: classinfopar.numofclassstu,
                    gradeid: classinfopar.gradeid,
                    numoforderbookstu:classinfopar.numoforderbookstu,
                    college:classinfopar.college,
                    grade: classinfopar.grade,
                    profession: classinfopar.profession,
                    campus:classinfopar.campus

                }}).then(
                function(response){//成功的回调
                    var addclassid=response.data;
                    if(addclassid=="-50000"){
                        alert("您刚刚所添加的年级不存在，请先添加相关年级或重新填写相关信息");

                        return;
                    } else if(addclassid=="-20000"){
                        alert("添加失败，重复添加该班级！");
                        return;
                    }
                    else if(addclassid=="-10000"){
                        alert("添加班级失败");
                        return;
                    }else{
                        alert("添加成功添加的id"+addgradeid);
                        _this.addclassinfo.classid=addclassid;
                        _this.classinfolists.push(_this.addclassinfo);
                    }

                },
                function(response){//失败
                    alert("添加成功添加的id"+addgradeid+response.data);
                }
            );
        },
        updateclassinfo:function(editeitem){//修改班级信息
            var editeitemindex = this.classinfolists.indexOf(editeitem);//找到当前被修改的班级信息
            var _this=this;
        //   alert("开始修改班级信息"+editeitemindex+(_this.classinfolists)[editeitemindex].numoforderbookstu);
            axios.get( _this.url+"/updateClass",{
                params:{
                    classid:editeitem.classid,
                    classname: editeitem.classname,
                    numofclassstu: editeitem.numofclassstu,
                    gradeid: editeitem.gradeid,
                    numoforderbookstu:editeitem.numoforderbookstu,
                    college:editeitem.college,
                    grade: editeitem.grade,
                    profession: editeitem.profession,
                    campus:editeitem.campus,
                    oldnumoforderbookstu:_this.classinfolists[editeitemindex].numoforderbookstu
                }}).then(
                function(response){//成功的回调
                    var result=response.data;
                    if(result<0){
                        alert("修改失败");
                        return;
                    }
                   else{
                        alert("修改成功");
                        _this.classinfolists.forEach(function (item,key) {
                            if(item.classid==editeitem.classid){
                                _this.set(_this.classinfolists,key,editeitem);
                            //    alert(item+"****"+key);
                            }
                        });
                    }
                },
                function(response){//失败
                    alert("添加成功添加的id"+addgradeid+response.data);
                }
            );
        },
        getitemdelete:function(url,deleteitem){//删除
            var _this=this;
            axios.get( _this.url+url, {
                params: {
                    classid:deleteitem.classid
                }
            }).then(
                function(response){
                    var r=response.data;
                    if((r!=-2) &&(r!=-1) ){
                        var index = _this.classinfolists.indexOf(deleteitem);
                        _this.classinfolists.splice(index,1);//
                    //    alert("删除成功");
                        }
                },
                function(response){
                    alert("连接服务器失败");
                });
        },


      /*  //添加班级TOdo
        lastaddclassinfo:function(){
            var _this=this;
            axios.get('/addClassInfo', {
                params: {
                    classid:null,
                    classname: _this.addclassinfo.classname,
                    numofclassstu: _this.addclassinfo.numofclassstu,
                    gradeid: _this.addclassinfo.gradeid,
                    numoforderbookstu:_this.addclassinfo.numoforderbookstu
                }

            }).then(function(response) {
                               // 这里是处理正确的回调

//得到添加后的gradeid
                var insertid=response.data;
                if(insertid>=0){
                   // alert("添加班级成功"+response.data);//getClassInfoId获取添加的班级的id
                    axios.get('/getClassInfoId', {  params: {
                        classid:null,
                        classname: _this.addclassinfo.classname,
                        numofclassstu: 0,
                        gradeid: _this.addclassinfo.gradeid,
                        numoforderbookstu:0
                    }

                    }).then(function(response) {
                        if(response.data>0){//找到班级id
                            _this.addclassinfo.classid=response.data;
                            _this.classinfolists.push(_this.addclassinfo);
                        }
                    },function(response) {
                        alert("服务器异常");
                    });



                }
                else
                {
                    alert("服务器异常");
                }


            }, function(response) {
                // 这里是处理错误的回调
                alert("添加失败");
            });
            _this.showModal=false;


        },
        //添加年级
        addgradeinfo:function(){
            var _this=this;
            axios.get('/addGradeInfo', {
                params: {
                    gradeid:null,
                    college: _this.addclassinfo.college,
                    grade: _this.addclassinfo.grade,
                    profession: _this.addclassinfo.profession,
                    numofclass:_this.addclassinfo.numofclass,
                    numofstu:_this.addclassinfo.numofclassstu,
                    campus:_this.addclassinfo.campus,
                    schoolSystem:_this.addclassinfo.schoolSystem
                }

            }).then(function(response) {
                // 这里是处理正确的回调
                //得到添加后的gradeid
                axios.get('/getGradeId',{  params: {
                    college: _this.addclassinfo.college,
                    grade: _this.addclassinfo.grade,
                    profession: _this.addclassinfo.profession,
                    campus:_this.addclassinfo.campus
                }
                }).then(
                    function(response){
                        //得到添加后的gradeid
                        var insertid=response.data;
                        if(insertid>=0){
                            //找年级id
                            _this.addclassinfo.gradeid=insertid;
                        //    alert("所添加年级的id"+insertid);
                        }
                        else
                        {
                            alert("服务器异常");
                        }
                    },
                    function(response){
                        alert("添加成功但加载id失败");
                    });


            }, function(response) {
                // 这里是处理错误的回调
                alert("添加失败");
            });

        },


*/

        getrecomendprofessionlists:function(newcollege){
            var _this = this;
            if(newcollege !=null){

                axios.get("/GradeinfoContrller/getProfessionByCollege",{  params: {college:newcollege}}).then(function(response){
                    _this.recomendprofessionlists=response.data;
                 //  alert("成功加载相关专业");
                });

            }
        },
        getCollegeList:function (){//点添加加载所有的学院

            var _this = this;
            axios.get("/GradeinfoContrller/getAllCollege").then(function(response){
                _this.Allcolleges=response.data;
              //  alert("学院成功"+ _this.Allcolleges);
            },function(errormessage){
                alert("失败"+ +errormessage);
            });

        },
        getList: function () {

            var _this = this;
            axios.get( _this.url+"/getAllClassinfo").then(function(response){
                _this.classinfolists=response.data;
       //   alert("成功"+ _this.classinfolists);
            },function(errormessage){
               // alert("失败"+ +errormessage);
            })
        }
    }
})
