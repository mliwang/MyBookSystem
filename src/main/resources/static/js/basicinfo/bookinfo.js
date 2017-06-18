/**
 * Created by 汪冉冉 on 2017/3/14.
 */
function addtoogle (){
    $('#modal-container-521717').modal('toogle');
};
var year=new Date().getFullYear();//获取当前年份
var app4 = new Vue({
    el: '#Myclassinfo',
    data: {
        url:"/BookinfoContrller",
       bookinfolists: [],//班级信息
        searchData:'',//搜索内容
        deleteitem:{},
        edititem:{},
        additem:{
            bookid:null,
            bookname:null,
            author:null,
            edition:null,
            publishunit:null,
            isbn:null,
            inventory:null,
            supplier:null
        }


    },

    mounted:function(){
        this.$nextTick(function(){
            this.getList();
        });
    },
    computed: {
             filteredbookinfo: function () {
            var self = this;
            return self.bookinfolists.filter(function (user) {
                return (user.bookname.indexOf(self.searchData) !== -1)||
                    (user.author.indexOf(self.searchData) !== -1)||
                    (user.publishunit.indexOf(self.searchData) !== -1)||
                    (user.isbn.indexOf(self.searchData) !== -1)
            })
        }
    },

    methods:{
        updatebookinfo:function(editeitem){//修改教材信息

            var _this=this;
            //   alert("开始修改班级信息"+editeitemindex+(_this.classinfolists)[editeitemindex].numoforderbookstu);
            axios.get(_this.url+"/updateBookByid",{
                params:{
                    bookid:editeitem.bookid,
                    bookname:editeitem.bookname,
                    author:editeitem.author,
                    edition:editeitem.edition,
                    publishunit:editeitem.publishunit,
                    isbn:editeitem.isbn,
                    inventory:editeitem.inventory,
                    supplier:editeitem.supplier
                }}).then(
                function(response){//成功的回调
                    var result=response.data;
                    if(result<0){
                        alert("修改失败");
                        return;
                    }
                    else{
                        alert("修改成功");
                        _this.bookinfolists.forEach(function (item,key) {
                            if(item.bookid==editeitem.bookid){
                                _this.set(_this.bookinfolists,key,editeitem);
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
            axios.get(_this.url+url, {
                params: {
                    bookid:deleteitem.bookid
                }
            }).then(
                function(response){
                    var r=response.data;
                    if(r>0){
                        var index = _this.bookinfolists.indexOf(deleteitem);
                        _this.bookinfolists.splice(index,1);//
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

        addbookitem:function(){//添加
            var _this = this;
            alert("添加教材"+_this.additem.bookname+ _this.additem.author+ _this.additem.edition);
            axios.get(_this.url+"/addBook",{
                params:{
                    bookid: null,
                    bookname: _this.additem.bookname,
                    author: _this.additem.author,
                    edition: _this.additem.edition,
                    publishunit: _this.additem.publishunit,
                    isbn: _this.additem.isbn,
                    inventory: _this.additem.inventory,
                    supplier:_this.additem.supplier
                }
            }).then(function(response){//连接服务器成功
               var r= response.data;
                if(r=="-10000"){//添加成功
                    alert("添加失败！")

                }
                else if(r==-20000){
                    alert("添加失败，添加重复！")
                }
                else {
                    _this.additem.bookid=r;
                    _this.bookinfolists.push(_this.additem);

                }

            },function(response){//连接服务器失败
                alert("连接服务器失败，请重新尝试");
            })
        },
        getList: function () {

            var _this = this;
            axios.get(_this.url+"/findAllBookinfo").then(function(response){
                _this.bookinfolists=response.data;
        //  alert("成功"+ _this.bookinfolists);
            },function(errormessage){
               alert("连接服务器失败，请重新尝试");
            })
        }
    }
})
