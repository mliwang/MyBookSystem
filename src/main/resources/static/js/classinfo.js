/**
 * Created by Administrator on 2017/3/14.
 */

var app4 = new Vue({
    el: '#Myclassinfo',
    data: {
       classinfolists: [],//年级信息
        searchData:'',//搜索内容
        Allcolleges:[],//所有学院信息
        AllProfession:[],//所有专业学院
        Allclassname:[],
        Allgrade:[year,year-1,year-2,year-3,year-4,year-5],//所有年级
        Allcampus:["黄家湖校区","昙华林校区"],
        addgradeinfo:{//添加班级信息
            college:'',
            grade:'',
            profession:'',
            campus:'',
            classname:'',
            numofclassstu:'',
            numoforderbookstu:''


        }

    },
    mounted:function(){
        this.$nextTick(function(){
            this.getList();
        });
    },
    computed: {
        filteredgradeinfo: function () {
            var self = this
            return self.classinfolists.filter(function (user) {
                return (user.college.indexOf(self.searchData) !== -1)||
                    (user.profession.indexOf(self.searchData) !== -1)||
                    (user.grade==self.searchData)||
                    (user.campus.indexOf(self.searchData) !== -1)
            })
        }
    },
    methods:{
        getCollegeList:function (){//点添加加载所有的
            var _this = this;
            axios.get("/getAllCollege").then(function(response){
                _this.Allcolleges=response.data;
                alert("学院成功"+ _this.Allcolleges);
            },function(errormessage){
                alert("失败"+ +errormessage);
            });

        },
        getList: function () {
            var _this = this;
            axios.get("/getAllClassinfo").then(function(response){
                _this.classinfolists=response.data;

               // alert("成功"+ _this.classinfolists);
            },function(errormessage){
               // alert("失败"+ +errormessage);
            })
        }
    }
})
