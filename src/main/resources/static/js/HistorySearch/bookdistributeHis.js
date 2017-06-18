/**
 * Created by 汪冉冉 on 2017/5/25.
 */
var year=new Date().getFullYear();//获取当前年份
var month=new Date().getMonth()+1;//获取当前月份
var app4 = new Vue({
    el: '#MyBookDishistory',
    data: {
        purchasingTime:'',
        timelists:[],
        searchData:'',
        recordMap:[],
        RecordLists:[],
    },
    mounted:function(){
        this.$nextTick(function(){
            this.initilaze();
        });
    },
    computed: {
      /*  filterlists: function () {
            var self = this;
            return self.RecordLists.filter(function (item) {
                return (item.supplier.indexOf(self.searchData) !== -1) ||
                    (item.bookcontent.indexOf(self.searchData) !== -1) ||
                    (item.bookmemuid.indexOf(self.searchData) !== -1) ||
                    (item.purchasetime.indexOf(self.searchData) !== -1)||
                    (item.booknum==self.searchData)
            })
        },
*/
    },
    watch:{
        'purchasingTime':function(){
            this.getbooklists();
        },
    },
    methods: {
        initilaze:function(){
            var _this=this;
            if(month<7){//year-1.9——year.1以前的教学计划都实施结束
                if (month<4){
                    this.purchasingTime=""+year-1+"/"+9+"-"+year+"/"+1;
                }
                else{
                    this.purchasingTime=""+year+"/"+2+"-"+year+"/"+7;}
                for(var temp=year;temp>year-5;temp--)
                { this.timelists.push(""+temp+"/"+9+"-"+(temp+1)+"/"+1);
                    this.timelists.push(""+temp+"/"+2+"-"+temp+"/"+7);}
            }
            else{
                if (month<11){
                    this.purchasingTime=""+year+"/"+2+"-"+year+"/"+7;
                }
                else{  this.purchasingTime=""+year-1+"/"+9+"-"+year+"/"+1;}
                for(var temp=year;temp<year-5;temp--)
                { this.timelists.push(""+temp+"/"+2+"-"+temp+"/"+7);
                    this.timelists.push(""+temp+"/"+9+"-"+(temp+1)+"/"+1);}
            };
            console.log(this.purchasingTime);
            _this.getbooklists();

        },
        getbooklists:function(){
            var _this=this;
            //列出该学期的购书清单
            axios.get("/HistoryContrller/findAllDistrirecords",{
                params: {
                    purchasingTime:_this.purchasingTime
                }}).then(
                function(response){
                    _this.recordMap=  response.data;
                //    alert(  _this.recordMap);
                },
                function(response){
                    alert("无法连接到服务器");
                }
            );

        }

    }
});




