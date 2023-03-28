//카테고리 nav
//화살표 버튼 누르면 세부 카테고리 나오는 것
window.onload = function () {    

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
}



//카테고리 호버링 드롭다운
window.onload = function () {
    var cate = document.querySelector("#category");
    //공공데이터 api에서 가져올 수도 있을듯..
    var cateInLiArr = [
        ["견과류", "국수", "냉동만두", "당면", "두부", "라면", "밀가루", "부침가루", "빵", "스프", "식빵", "시리얼", "즉석국", "즉석덮밥", "즉석밥", "즉석죽", "컵라면", "컵밥", "탕"],
        ["계란", "닭고기", "돼지고기", "쇠고기"],
        ["맛김", "맛살", "어묵", "참치캔"],
        ["마가린", "발효유", "분유", "소시지", "우유", "치즈", "햄"],
        ["간장", "고추장", "된장", "잼", "마요네즈", "꿀", "설탕", "소금", "식용유", "식초", "쌈장", "참기름", "카레", "케첩", "혼합조미료", "드레싱"],
        ["감자", "고구마", "깻잎", "단무지", "당근", "대파", "마늘", "무", "배추", "버섯", "상추", "시금치", "양배추", "양파", "오이", "쪽파", "콩나물", "포기김치", "풋고추", "호박"],
        ["감자칩", "껌", "모나카류아이스크림", "빙과류", "새우깡", "아이스크림", "에너지바", "초코바", "초코파이", "초콜릿", "사탕", "비스킷"],
        ["주스", "차", "두유", "막걸리", "맥주", "비타민음료", "사이다", "생수", "소주", "에너지음료", "이온음료", "캔커피", "커피믹스", "콜라", "포도주"],
        ["화장지", "바디워시", "로션", "생리대", "비누", "손세정제", "염색약", "면도날", "샴푸", "종이기저귀", "치약", "칫솔", "구강청결제", "마스크", "코로나 자가검사 키트"],
        ["고무장갑", "랩", "부탄가스", "살균소독제", "살충제","섬유유연제", "섬유탈취제", "세정제", "세탁세제(가루형)", "세탁세제(액체형)", "습기제거제", "위생배", "주방세제", "키친타올"],
        ["갈치", "고등어", "오징어", "조기"]
    ];

    var dropDown = function () {
        var cateHoverDiv = document.querySelector(".cate_hover_div");
        cateHoverDiv.style.display = "block";
    };

    var dropUp = function (e) {
        var cateHoverDiv = document.querySelector(".cate_hover_div");
        console.log("e.pageY", e.pageY);
        console.log("e.screenY", e.screenY);

        if(e.pageY <= 148 || e.pageY >= 638) {
            cateHoverDiv.style.display = "none";
        }
    }
      
    var hoverIn = function () {
        var cateInUl = this.querySelector(".cate_in_ul");
        console.log("this:", this);

        if (cateInUl) {
            cateInUl.style.display = "block";
        }
    };
      
      
      var cate = document.querySelector(".cate");
      cate.addEventListener("mouseover", dropDown);
      cate.addEventListener("mouseout", dropUp);
      
      var cateHoverUl = document.querySelector(".cate_hover_ul");
      cateHoverUl.addEventListener("mouseover", hoverIn);


};

