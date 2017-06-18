/**
 * Created by 汪冉冉 on 2017/5/25.
 */
var year=new Date().getFullYear();//获取当前年份
var month=new Date().getMonth()+1;//获取当前月份
var app4 = new Vue({
    el: '#bookPurchase',
    data: {
        purchasingTime:'',
        searchData:'',
        timelists:[],
        ClassLists:[],


        choseClass:{}
          },

    mounted:function(){
        this.$nextTick(function(){
             this.initilaze();
        });
    },
    computed: {
      filterclass: function () {
            var self = this;
            return self.ClassLists.filter(function (item) {
                return (item.plans[0].gradeinfo.college.indexOf(self.searchData) !== -1) ||
                    (item.plans[0].gradeinfo.profession.indexOf(self.searchData) !== -1) ||
                    (item.plans[0].gradeinfo.campus.indexOf(self.searchData) !== -1) ||
                    (item.classinfo.classname.indexOf(self.searchData) !== -1) ||
                    (item.plans[0].gradeinfo.grade==self.searchData)||
                    (item.plans[0].semester==self.searchData)
                               })
        },

    },
    watch:{
        'purchasingTime':function(){

            this.getclasslists();
        },


    },
    methods: {
        print:function(){//点打印，完成页面加载和记录录入
            var _this=this;
            $.ajax({
                type: "post",
                url: "/BookPurchasingContrller/printDistribute",
                contentType: "application/json",
                async: false,
                data: JSON.stringify(_this.choseClass),
                success: function (jsonResult) {
                    console.log("成功");
                    // alert(jsonResult);
                    top.document.write(jsonResult);
                    top.document.close();

                }

            });


        },
        initilaze:function(){
            var _this=this;
         if(month<7){//year-1.9——year.1以前的教学计划都实施结束
                if (month<4){
                    this.purchasingTime=""+year+"/"+2+"-"+year+"/"+7;
                }
                else{
                    this.purchasingTime=""+year+"/"+9+"-"+(year+1)+"/"+1;}
                for(var temp=year;temp<year+3;temp++)
                { this.timelists.push(""+temp-1+"/"+9+"-"+temp+"/"+1);
                this.timelists.push(""+temp+"/"+2+"-"+temp+"/"+7);}
            }
          else{
                if (month<11){
                    this.purchasingTime=""+year+"/"+9+"-"+(year+1)+"/"+1;
                }
                else{  this.purchasingTime=""+year+1+"/"+2+"-"+year+1+"/"+7;}
                for(var temp=year;temp<year+3;temp++)
                { this.timelists.push(""+temp+"/"+2+"-"+temp+"/"+7);
                    this.timelists.push(""+temp+"/"+9+"-"+(temp+1)+"/"+1);}
            };
           console.log(this.purchasingTime);
            _this.getclasslists();

        },
        getclasslists:function(){
            var _this=this;
            //列出该学期的购书清单
            axios.get("/BookPurchasingContrller/findAllclassneedDistribute",{
                params: {
                    purchasingTime:_this.purchasingTime
                }}).then(
                function(response){
                    _this.ClassLists=  response.data;
                //  alert( _this.ClassLists);
                                  },
                function(response){
                    alert("无法连接到服务器");
                }
            );

        }

    }
});




