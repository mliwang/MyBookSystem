/**
 * Created by 汪冉冉 on 2017/5/25.
 */

var app4 = new Vue({
    el: '#MyPlaning',
    data: {
        leftsearch:'',//搜索框
        courselists:[],//所有课程计划
        leftchoseCourse:{},//左部选中的课程计划


        rightsearch:'',//专业模糊查找关键字
        perfessionlists:[],//专业列表
        choseperfessionlists:[],//已选中的专业
        checkAllflag:false,

        edcoursename:'',//新更改的课程名称
        newbookid:'',//新教材id
        mybookswhenupdate:[],//加载的教材列表
        planlists:[],
        booknameClue:'',

          },
    computed: {
        filtercourselists: function () {
            var self = this;
            return self.courselists.filter(function (item) {
                return (item.coursename.indexOf(self.leftsearch) !== -1) ||
                    (item.bookname.indexOf(self.leftsearch) !== -1) ||
                    (item.author.indexOf(self.leftsearch) !== -1) ||
                    (item.publishunit.indexOf(self.leftsearch) !== -1) ||
                    (item.isbn.indexOf(self.leftsearch) !== -1) ||
                    (item.supplier.indexOf(self.leftsearch) !== -1) ||
                    (item.publishunit.indexOf(self.leftsearch) !== -1)
            })
        },
        filterperfessionlists:function(){
            var self = this;
            return self.perfessionlists.filter(function (item) {
                return (
                    (item.gradeinfo.college.indexOf(self.rightsearch) !== -1)||
                    (item.gradeinfo.profession.indexOf(self.rightsearch) !== -1)||
                    (item.gradeinfo.grade==self.rightsearch)||
                    (item.gradeinfo.campus.indexOf(self.rightsearch) !== -1)||
                    (item.semester==self.rightsearch))
            })
        },
          },

    watch: {
       'leftchoseCourse' :function(){
           var _this=this;

           this.choseCourse();
       },
        'rightsearch' :function(){//
            this.checkAllflag=false;
        }


    },
    mounted:function(){
        this.$nextTick(function(){
this.getCoursePlans();
        });
    },
    methods:{
        ChanggebookInplan:function(edcoursename,newbookid){//更改选定教学计划中的教材,edcoursename为新的课程名称,newbookid为新的教材id
            var _this = this;
            if ( _this.choseperfessionlists.length<=0){alert("请选择需要换教材的专业");return}
            if(newbookid==null||newbookid==''){alert("请选择新书");return}
            console.log("换书");
            var lists=[];//最终要传的
            _this.choseperfessionlists.forEach(function (item) {//删减对应属性,拼接出Teachingplan
                var teachingplan={planid:item.planid,
                    gradeid:item.gradeid,
                    coursename:edcoursename,
                    bookid :newbookid,
                    semester:item.semester};
                lists.push(teachingplan);
            });
            console.log(lists);
            axios.post("/CoursePlanContrller/UpdatebookInplan",lists//如果要传个数组和其他的可以像后面这样传{plans: _this.choseperfessionlists, edcoursename:edcoursename, newbookid:newbookid}
            ).then(function(response){//连接服务器成功
                var r= response.data;
                if(r){alert("换书成功");}
                else
                {
                    alert("换书失败");
                }
            },function(response){//连接服务器失败
                alert("连接服务器失败，请重新尝试");
            })

        },
        getbooks:function(booknameClue){//找到相关教材
            var _this = this;
            axios.get("/BookinfoContrller/findbookBybooknmae",{
                params: {
                    bookname:booknameClue
                }}).then(
                function(response){

                 _this.mybookswhenupdate=response.data;
                },
                function(response){
                    alert("无法连接到服务器");
                }
            );

        },




        checkAll:function(){
            var _this=this;
            var isCheck=this.checkAllflag;
                _this.filterperfessionlists.forEach(function (item) {
                    var index= _this.choseperfessionlists.indexOf(item);
                    item.checked = !isCheck;
                    if(!isCheck&&index==-1) {//全选,而且该项不在已选中
                      _this.choseperfessionlists.push(item);

                    }
                    if (isCheck&&index!==-1){//取消全选，且该项在已选列中
                        _this.choseperfessionlists.splice(index,1);

                    }

                    });
            this.checkAllflag=!isCheck;

        },
       clickchoseone:function(item1){//点一个
            var _this=this;
           var oldvalue=item1.checked;
           var count = 0;
           this.filterperfessionlists.forEach(function (item) {
               if (item.planid==item1.planid){
                   item.checked=!oldvalue;
               }
               if(item.checked){
                   count++;
               }
           });
           console.log("原值"+oldvalue+"已选中项"+count);
         if(!oldvalue&&count==_this.filterperfessionlists.length){//全部被选中
             this.checkAllflag=true;
         }
           if(oldvalue&&count==_this.filterperfessionlists.length-1){//打破全选
               this.checkAllflag=false;
           }
        },

        choseCourse:function(){//选定课程，加载使用该教材的专业，及使用该课程的学期
            this.checkAllflag=false;

            this.choseperfessionlists=[];
var _this=this;
            axios.get("/CoursePlanContrller/findPerfessionLists",{
                params:{
                    bookid:_this.leftchoseCourse.bookid
                }
            }).then(
                function(response){
                    _this.perfessionlists=response.data;
                    _this.perfessionlists.forEach(function (item) {//给所有的专业计划注册一个checked属性
                        _this.$set(item,"checked",false);
                       });

                },
                function(response){
                    alert("无法连接到服务器");
                }
            );


        },

        getCoursePlans:function(){//找到所有的课程计划
            var _this = this;
            axios.get("/CoursePlanContrller/findAllCoursePlan").then(
                function(response){
                    _this.courselists=response.data;
                },
                function(response){
                    alert("无法连接到服务器");
                }
            );
        },

    }
})




