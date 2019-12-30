/** 定义控制器层 */
app.controller('itemCatController', function($scope, $controller, baseService){

    /** 指定继承baseController */
    $controller('baseController',{$scope:$scope});

  /** 查询条件对象 */
    $scope.searchEntity = {};
    // 定义变量记录父级ID
    $scope.parentId= 0;
    /** 根据上级ID查询(查询条件) */
    $scope.findItemCatByParentId=function (parentId) {
        // 设置父级id
        $scope.parentId = parentId;
        baseService.sendGet("/itemCat/findItemCatByParentId",
            "parentId="+ parentId).then(function (response) {
            $scope.dataList = response.data;
        });
    };


    /** 添加或修改 */
    $scope.saveOrUpdate = function(){
        var url = "save";
        if ($scope.itemCat.id){
            url = "update";
        }else {
            // 添加时设置父级ID
            $scope.itemCat.parentId = $scope.parentId;
        }
        /** 发送post请求 */
        baseService.sendPost("/itemCat/" + url, $scope.itemCat)
            .then(function(response){
                if (response.data){
                    /** 重新加载数据 */
                    $scope.findItemCatByParentId($scope.parentId);
                    $scope.itemCat = null;
                }else{
                    alert("操作失败！");
                }
            });
    };

    /** 显示修改 */
    $scope.show = function(entity){
       /** 把json对象转化成一个新的json对象 */
     // $scope.entity = JSON.parse(JSON.stringify(entity));
       $scope.itemCat = JSON.parse(JSON.stringify(entity));
    };

    /** 批量删除 */
    $scope.delete = function(){
        if ($scope.ids.length > 0){
            baseService.deleteById("/itemCat/delete", $scope.ids)
                .then(function(response){
                    if (response.data){
                        /** 重新加载数据 */
                        $scope.findItemCatByParentId($scope.parentId);
                    }else{
                        alert("删除失败！");
                    }
                });
        }else{
            alert("请选择要删除的记录！");
        }
    };
    //默认为1级
    $scope.grade=1;
    // 查询下级
    $scope.selectList=function (entity,grade) {
        $scope.grade = grade;
        if (grade == 1){
            $scope.itemCat_1 = null;
            $scope.itemCat_2 = null;
        }
        if (grade == 2){
            $scope.itemCat_1 = entity;
            $scope.itemCat_2 = null;
        }
        if (grade == 3){
            $scope.itemCat_2 = entity;
        }
        /** 查询此级下级列表 */
        $scope.findItemCatByParentId(entity.id);
    };
   // 查询类型模板列表
    $scope.findTypeTemplateList=function () {
        baseService.sendGet("/typeTemplate/findTypeTemplateList").then(function (response) {
           $scope.typeTemplateList = response.data;
        });
    };

});