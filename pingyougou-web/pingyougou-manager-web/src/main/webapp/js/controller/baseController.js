//定义基础控制器层
app.controller('baseController',function ($scope) {
    /** 分页指令配置信息对象  */
    $scope.paginationConf= {
        currentPage: 1, // 当前页码
        totalItems: 100 ,  // 总记录数
        itemsPerPage: 10, // 每页显示的记录数
        numberOfPages:[10,15,20,25], // 页码下拉列表框
        onChange:function () { // 改变事件
            $scope.reload(); //重新加载
        }
    };

    /** 重新加载列表数据 */
    $scope.reload=function () {
        /** 切换页码  */
        $scope.search($scope.paginationConf.currentPage,
            $scope.paginationConf.itemsPerPage);
    };
//定义数组
    $scope.ids=[];
    //为数组绑定点击事件
    $scope.updateSelection=function ($event,id) {
        /** 如果是被选中,则增加到数组 */
        if($event.target.checked){
            $scope.ids.push(id);

        }else {
            var idx=$scope.ids.indexOf(id);
            /** 删除数组中的元素  */
            $scope.ids.splice(idx,1);
        }
    };
    // 提取数组中json某个属性，返回拼接的字符串（逗号分隔）
    $scope.jsonArr2Str =function (jsonArrStr,key) {
        //把JsonArrStr转化成数组对象
        var jsonArr = JSON.parse(jsonArrStr);
        // 定义新数组
        var resArr =[];
        //迭代Json 数组
        for(var i=0; i <jsonArr.length; i++){
            // 取出数组中的每个元素
            var json =jsonArr[i];
            // 把json对象的值添加到数组
            resArr.push(json[key]);
        }
        // 返回数组中的元素用逗号nh分隔的字符串
        return resArr.join(",");
    }
});