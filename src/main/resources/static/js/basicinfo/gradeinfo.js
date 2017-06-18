/**
 * Created by Administrator on 2017/3/14.
 */

    var year=new Date().getFullYear();//获取当前年份
var app4 = new Vue({
    el: '#Mygradeinfo',
    data: {
        url:"/GradeinfoContrller",
        gradeinfolists: [],//年级信息
        Allcolleges:[],//所有学院信息
        AllProfession:[],//所有专业学院
        Allgrade:[year,year-1,year-2,year-3,year-4,year-5],//所有年级
        Allcampus:["黄","昙"],
        AllschoolSystem:[4,5,7],
        searchData:'',//搜索内容
        newstudentgrade:'',//入学新生的年级
        deleteitem:{},
        addgradeinfo:{//添加年级信息
            gradeid:null,
            college:null,
            grade:null,
            profession:null,
            campus:null,
            numofclass:null,
            schoolSystem:null,
        }
    },
    computed: {
        filteredgradeinfo: function () {
            var self = this;
            return self.gradeinfolists.filter(function (user) {
                return (user.college.indexOf(self.searchData) !== -1)||
                    (user.profession.indexOf(self.searchData) !== -1)||
                    (user.grade==self.searchData)||
                    (user.campus.indexOf(self.searchData) !== -1)
            });
        },

    },
    mounted:function(){
        this.$nextTick(function(){
            this.getList();


        });
    },
    methods:{
        //点击添加加载学院列表,专业列表
        getCollegeList:function(){
            var _this = this;
            axios.get( _this.url+"/getAllCollege").then(function(response){
                _this.Allcolleges=response.data;
           //     alert("学院成功"+ _this.Allcolleges);
            },function(errormessage){
                alert("失败"+ +errormessage);
            });
            //加载所有专业
            axios.get( _this.url+"/getAllProfession").then(function(response){
                _this.AllProfession=response.data;
            //    alert("专业成功"+ _this.AllProfession);
            },function(errormessage){
                alert("失败"+ +errormessage);
            })

        },

//添加条目
        myaddgradeinfo:function(){
          //  alert("进入添加****");
            var _this=this;
            var newaddgradeinfo=this.addgradeinfo;
            axios.get(_this.url+'/addGradeInfo', {
                params: {
                    gradeid:null,
                    college:newaddgradeinfo.college,
                    grade:newaddgradeinfo.grade,
                    profession: newaddgradeinfo.profession,
                    numofclass:newaddgradeinfo.numofclass,
                    numofstu:0,
                    campus:newaddgradeinfo.campus,
                    schoolSystem:newaddgradeinfo.schoolSystem
                }

            }).then(
                function(response) {
                // 这里是处理正确的回调
                 //   $.(_this.$els.addmodel).modal('toogle');
               // alert("添加成功******"+response.data);
//得到添加后的gradeid
                var addgradeid=response.data;
                    if (addgradeid=="-1000"){
                        alert("添加失败!");
                        }
                    else if (addgradeid=="-2000"){
                        //添加重复
                        alert("该年级已存在添加重复!");
                    }
                    else{

                        //  alert("添加成功添加的id"+addgradeid);
                        addgradeid.numofclassstu=0;
                        _this.addgradeinfo.gradeid=addgradeid;
                        _this.gradeinfolists.push(_this.addgradeinfo);

                    }


            },
                function(response) {
                // 这里是处理错误的回调
                alert("添加失败");
            });

        },

        //删除选中条目
        getitemdelete:function(){
            var _this=this;
            axios.get(_this.url+"/delete", {
                params: {
                    gradeid: _this.deleteitem.gradeid
                }
            }).then(
                function(response){

                   var r=response.data;
                    if((r!=-2) &&(r!=-1) ){
                        var index =  _this.gradeinfolists.indexOf( _this.deleteitem);
                        _this.gradeinfolists.splice(index,1);//删除当前联系人
                        alert("删除成功");}

                },
                function(response){
                    alert("无法连接到服务器");
                });
        },

        getList: function () {
            var _this = this;
            axios.get(_this.url+'/getAllGrade').then(function(response){
                _this.gradeinfolists=response.data;
          //  alert("成功"+ _this.gradeinfolists);
            },function(errormessage){
           //     alert("失败"+ +errormessage);
            })
        },
        enternewstudents: function (){
            var _this = this;
            axios.get('/enterNewStudent', {
                params: {
                    grade: _this.newstudentgrade
                }
            }).then(function(response){
              var r= response.data;
               if ( r){ alert("新生入学成功");}
                else {
                   alert("新生入学失败");
               }

            },function(errormessage){
                   alert("连接服务器失败");
            })
        }
    }
})
