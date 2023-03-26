//카테고리 nav
//화살표 버튼 누르면 세부 카테고리 나오는 것
var cateNavBts = document.querySelectorAll(".cate_nav_bt");
var cateDetailUls = document.querySelectorAll(".cate_nav_ul");
var cateLis = document.querySelectorAll(".cate_nav_item");

var btRotationOpen = function (e) {
    e.target.style.transition = "all 0.2s ease-in-out 0s";
    const index = Array.from(cateNavBts).indexOf(e.currentTarget);

    console.log('e.currentTarget:', e.currentTarget);
    console.log('e.target:', e.target);
    console.log('index:', index);
    console.log('cateDetailUls: ', cateDetailUls);

    if(e.target.style.transform === "rotate(180deg)") {
        e.target.style.transform = "rotate(0deg)";  

        cateDetailUls[index].style.maxHeight = "0px";
        cateDetailUls[index].style.opacity = 0;

        cateLis[index].style.height = "35px";

        var detailsCateLis = cateDetailUls[index].querySelectorAll("li");
        detailsCateLis.forEach((li) => {
            li.style.visibility = "hidden";
        });    
       
    } else {
        e.target.style.transform = "rotate(180deg)";

        console.log('e.currentTarget:', e.currentTarget);
        console.log('e.target:', e.target);
        console.log('index:', index);
        console.log('cateDetailUls: ', cateDetailUls);

        cateDetailUls[index].style.maxHeight = "100vh";
        cateDetailUls[index].style.opacity = 1;

        cateLis[index].style.height = (parseInt(getComputedStyle(cateDetailUls[index]).height) + 50) + 'px';
        cateLis[index].style.transition = "all 0.1s ease-in-out 0s";

        var detailsCateLis = cateDetailUls[index].querySelectorAll("li");
        detailsCateLis.forEach((li) => {
            li.style.visibility = "visible";
        });        
    } //if-else
   

};

cateNavBts.forEach((button) => {
    button.addEventListener("click", btRotationOpen);
}) //.forEach

    // cateNavBt.style.maxHeight = "200px";
    // cateNavBt.style.opacity = 1;