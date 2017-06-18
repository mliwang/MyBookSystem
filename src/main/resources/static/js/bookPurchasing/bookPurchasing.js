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
        bookLists:[],

        checkAllflag:false,
        chosebookLists:[]
          },

    mounted:function(){
        this.$nextTick(function(){
             this.initilaze();
        });
    },/* public Fullbookrecords(String bookmemuid, String bookid, String supplier, Integer booknum, String purchasetime, Bookinfo bookinfo) {
     */
    computed: {
        filterbookNeedBuylists: function () {
            var self = this;
            return self.bookLists.filter(function (item) {
                return (item.supplier.indexOf(self.searchData) !== -1) ||
                    (item.bookinfo.bookname.indexOf(self.searchData) !== -1) ||
                    (item.bookinfo.author.indexOf(self.searchData) !== -1) ||
                    (item.bookinfo.publishunit.indexOf(self.searchData) !== -1) ||
                    (item.bookinfo.isbn.indexOf(self.searchData) !== -1) ||
                    (item.purchasetime.indexOf(self.searchData) !== -1)
            })
        },
   /* Lists:function(){
        var _this=this;
        var lists=[];//最终要传的
        _this.chosebookLists.forEach(function (item) {//删减对应属性,拼接出Fullbookrecords
            var Fullbookrecords={bookmemuid:item.bookmemuid,
                bookid:item.bookid,
                supplier:item.supplier,
                booknum :item.booknum,
                purchasetime:item.purchasetime,
                bookinfo:{
                    bookid:item.bookinfo.bookid,
                    bookname:item.bookinfo.bookname,
                    author:item.bookinfo.author,
                    edition:item.bookinfo.edition,
                    publishunit:item.bookinfo.publishunit,
                    isbn:item.bookinfo.isbn,
                    inventory:item.bookinfo.inventory,
                    supplier:item.bookinfo.supplier,
                }
            };
            lists.push(Fullbookrecords);
        });
        return JSON.stringify(lists);
    }*/

    },
    watch:{
        'purchasingTime':function(){
            this.checkAllflag=false;
            this.getbooklists();
        },
        'searchData' :function(){
            this.checkAllflag=false;
        }

    },
    methods: {
    print:function(){//打印
            var _this=this;
          if( _this.chosebookLists.length<=0) {
              alert("请至少选择一条记录");
              return;
          }
            var lists=[];//最终要传的
             _this.chosebookLists.forEach(function (item) {//删减对应属性,拼接出Fullbookrecords
                var Fullbookrecords={bookmemuid:item.bookmemuid,
                    bookid:item.bookid,
                    supplier:item.supplier,
                    booknum :item.booknum,
                    purchasetime:item.purchasetime,
                    bookinfo:{
                        bookid:item.bookinfo.bookid,
                        bookname:item.bookinfo.bookname,
                        author:item.bookinfo.author,
                        edition:item.bookinfo.edition,
                        publishunit:item.bookinfo.publishunit,
                        isbn:item.bookinfo.isbn,
                        inventory:item.bookinfo.inventory,
                        supplier:item.bookinfo.supplier,
                    }
                };
                lists.push(Fullbookrecords);
            });
            console.log("点击了"+lists);
            $.ajax({
                type: "post",
                url: "/BookPurchasingContrller/printPurchasing",
                contentType: "application/json",
                async: false,
                data: JSON.stringify(lists),
                success: function (jsonResult) {
                    console.log("成功");
                   // alert(jsonResult);
                    top.document.write(jsonResult);
                    top.document.close();
                  //  window.open(jsonResult);

                }

            });


        },
        checkAll:function(){
            var _this=this;
            var isCheck=this.checkAllflag;
            _this.filterbookNeedBuylists.forEach(function (item) {
                var index= _this.chosebookLists.indexOf(item);
                item.checked = !isCheck;
                if(!isCheck&&index==-1) {//全选,而且该项不在已选中
                    _this.chosebookLists.push(item);

                }
                if (isCheck&&index!==-1){//取消全选，且该项在已选列中
                    _this.chosebookLists.splice(index,1);

                }

            });
            this.checkAllflag=!isCheck;

        },
        clickchoseone:function(item1){//点一个
            var _this=this;
            var oldvalue=item1.checked;
            var count = 0;
            this.filterbookNeedBuylists.forEach(function (item) {
                if (item.bookid==item1.bookid){
                    item.checked=!oldvalue;
                }
                if(item.checked){
                    count++;
                }
            });
            console.log("原值"+oldvalue+"已选中项"+count);
            if(!oldvalue&&count==_this.filterbookNeedBuylists.length){//全部被选中
                this.checkAllflag=true;
            }
            if(oldvalue&&count==_this.filterbookNeedBuylists.length-1){//打破全选
                this.checkAllflag=false;
            }
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
            _this.getbooklists();

        },
        getbooklists:function(){
            var _this=this;
            //列出该学期的购书清单
            axios.get("/BookPurchasingContrller/findAllneedBuy",{
                params: {
                    purchasingTime:_this.purchasingTime
                }}).then(
                function(response){
                    _this.bookLists=  response.data;
                    _this.bookLists.forEach(function (item) {//给所有的购书记录注册一个checked属性
                        _this.$set(item,"checked",false);
                    });

                },
                function(response){
                    alert("无法连接到服务器");
                }
            );

        }

    }
});




