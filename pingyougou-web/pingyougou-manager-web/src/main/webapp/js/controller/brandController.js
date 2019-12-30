//控制层
app.controller('brandController',function ($scope,$controller,baseService) {
  // 指令继承baseController
    $controller('baseController',{$scope:$scope});

    /** 读取品牌数据绑定到表格中 */
   $scope.findAll=function () {
        // 调用服务层查询所有品牌数据
        baseService.sendGet('/brand/findAll').then(function (response) {
            $scope.dataList=response.data;
        });
    };
    //定义搜索对象
  $scope.searchEntity={};

    /** 分页查询品牌信息 */
    $scope.search=function (page,rows) {
        /** 发送异步请求分页查询品牌数据 */
       baseService.findByPage('/brand/findByPage',page,
           rows,$scope.searchEntity).then(function (response) {
            $scope.dataList=response.data.rows;
            /** 更新总记录数 */
            $scope.paginationConf.totalItems=response.data.total;
        })
    };

    //添加和修改品牌
    $scope.saveOrUpdate= function () {
        //定义请求URL
        var url ="save"; //添加品牌
        if($scope.entity.id){
            url="update"
        }

        //发送post请求
        baseService.sendPost("/brand/"+url,$scope.entity).then(function (response) {
            if (response.data){
                // 重新加载品牌数据
                $scope.reload();
            }else {
                alert("操作失败！");
            }
        });
    };

    //修改方法点击事件
    $scope.show =function (entity) {
        //把entity的json对象转化成一个新的Json对象
        $scope.entity = JSON.parse(JSON.stringify(entity));
    };

    /** 批量删除  */
    $scope.delete=function () {
        if($scope.ids.length>0){
            /** 发送异步请求 */
            if(confirm("你确定要删除吗？")){
                baseService.deleteById('/brand/delete',$scope.ids).then(
                    function (response) {
                        if(response.data){
                            $scope.reload();
                            $scope.ids=[];
                        }
                    }
                );

            }else {
                alert("删除失败")
            }

        }else (
            alert("请选着要删除的！")
        )
    }
});
