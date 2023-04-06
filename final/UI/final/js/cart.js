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

    //카테고리 호버링 드롭다운
    var cate = document.querySelector("#category");
    //공공데이터 api에서 가져올 수도 있을듯.. 이거 아무래도 ajax인데.. .ㅋㅋㅋㅋㅋ
    var cateInLiArr = [
        [{cateId: 1, cateName: "견과류"}, {cateId: 2, cateName: "국수"}, {cateId: 3, cateName: "냉동만두"}, {cateId: 4, cateName: "당면"}, {cateId: 5, cateName: "두부"}, {cateId: 6, cateName: "라면"}, {cateId: 7, cateName: "밀가루"}, {cateId: 8, cateName: "부침가루"}, {cateId: 9, cateName: "빵"}, {cateId: 10, cateName: "스프"}, {cateId: 11, cateName: "식빵"}, {cateId: 12, cateName: "시리얼"}, {cateId: 13, cateName: "즉석국"}, {cateId: 14, cateName: "즉석덮밥"}, {cateId: 15, cateName: "즉석밥"}, {cateId: 16, cateName: "즉석죽"}, {cateId: 17, cateName: "컵라면"}, {cateId: 18, cateName: "컵밥"}, {cateId: 19, cateName: "탕"}],
        [{cateId: 20, cateName: "계란"}, {cateId: 21, cateName: "닭고기"}, {cateId: 22, cateName: "돼지고기"}, {cateId: 23, cateName: "쇠고기"}],
        [{cateId: 24, cateName: "맛김"}, {cateId: 25, cateName: "맛살"}, {cateId: 26, cateName: "어묵"}, {cateId: 27, cateName: "참치캔"}],
        [{cateId: 28, cateName: "마가린"}, {cateId: 29, cateName: "발효유"}, {cateId: 30, cateName: "분유"}, {cateId: 31, cateName: "소시지"}, {cateId: 32, cateName: "우유"}, {cateId: 33, cateName: "치즈"}, {cateId: 34, cateName: "햄"}],
        [{cateId: 34, cateName: "간장"}, {cateId: 35, cateName: "고추장"}, {cateId: 36, cateName: "된장"}, {cateId: 37, cateName: "잼"}, {cateId: 38, cateName: "마요네즈"}, {cateId: 39, cateName: "꿀"}, {cateId: 40, cateName: "설탕"}, {cateId: 41, cateName: "소금"}, {cateId: 42, cateName: "식용유"}, {cateId: 43, cateName: "식초"}, {cateId: 44, cateName: "쌈장"}, {cateId: 45, cateName: "참기름"}, {cateId: 46, cateName: "카레"}, {cateId: 47, cateName: "케첩"}, {cateId: 48, cateName: "혼합조미료"}, {cateId: 49, cateName: "드레싱"}],
        [{cateId: 50, cateName: "감자"}, {cateId: 51, cateName: "고구마"}, {cateId: 52, cateName: "깻잎"}, {cateId: 53, cateName: "단무지"}, {cateId: 54, cateName: "당근"}, {cateId: 55, cateName: "대파"}, {cateId: 56, cateName: "마늘"}, {cateId: 57, cateName: "무"}, {cateId: 58, cateName: "배추"}, {cateId: 59, cateName: "버섯"}, {cateId: 60, cateName: "상추"}, {cateId: 61, cateName: "시금치"}, {cateId: 62, cateName: "양배추"}, {cateId: 63, cateName: "양파"}, {cateId: 64, cateName: "오이"}, {cateId: 65, cateName: "쪽파"}, {cateId: 66, cateName: "콩나물"}, {cateId: 67, cateName: "포기김치"}, {cateId: 68, cateName: "풋고추"}, {cateId: 69, cateName: "호박"}],
        [{cateId: 70, cateName: "감자칩"}, {cateId: 71, cateName: "껌"}, {cateId: 72, cateName: "모나카류아이스크림"}, {cateId: 73, cateName: "빙과류"}, {cateId: 74, cateName: "새우깡"}, {cateId: 75, cateName: "아이스크림"}, {cateId: 76, cateName: "에너지바"}, {cateId: 77, cateName: "초코바"}, {cateId: 78, cateName: "초코파이"}, {cateId: 79, cateName: "초콜릿"}, {cateId: 80, cateName: "사탕"}, {cateId: 81, cateName: "비스킷"}],
        [{cateId: 81, cateName: "주스"}, {cateId: 82, cateName: "차"}, {cateId: 83, cateName: "두유"}, {cateId: 84, cateName: "막걸리"}, {cateId: 85, cateName: "맥주"}, {cateId: 86, cateName: "비타민음료"}, {cateId: 87, cateName: "사이다"}, {cateId: 88, cateName: "생수"}, {cateId: 89, cateName: "소주"}, {cateId: 90, cateName: "에너지음료"}, {cateId: 91, cateName: "이온음료"}, {cateId: 92, cateName: "캔커피"}, {cateId: 93, cateName: "커피믹스"}, {cateId: 94, cateName: "콜라"}, {cateId: 95, cateName: "포도주"}],
        [{cateId: 96, cateName: "화장지"}, {cateId: 97, cateName: "바디워시"}, {cateId: 98, cateName: "로션"}, {cateId: 99, cateName: "생리대"}, {cateId: 100, cateName: "비누"}, {cateId: 101, cateName: "손세정제"}, {cateId: 102, cateName: "염색약"}, {cateId: 103, cateName: "면도날"}, {cateId: 104, cateName: "샴푸"}, {cateId: 105, cateName: "종이기저귀"}, {cateId: 106, cateName: "치약"}, {cateId: 107, cateName: "칫솔"}, {cateId: 108, cateName: "구강청결제"}, {cateId: 109, cateName: "마스크"}, {cateId: 110, cateName: "코로나 자가검사 키트"}],
        [{cateId: 111, cateName: "고무장갑"}, {cateId: 112, cateName: "랩"}, {cateId: 113, cateName: "부탄가스"}, {cateId: 114, cateName: "살균소독제"}, {cateId: 115, cateName: "살충제"},{cateId: 116, cateName: "섬유유연제"}, {cateId: 117, cateName: "섬유탈취제"}, {cateId: 118, cateName: "세정제"}, {cateId: 119, cateName: "세탁세제(가루형)"}, {cateId: 120, cateName: "세탁세제(액체형)"}, {cateId: 121, cateName: "습기제거제"}, {cateId: 122, cateName: "위생백"}, {cateId: 123, cateName: "주방세제"}, {cateId: 124, cateName: "키친타월"}],
        [{cateId: 125, cateName: "갈치"}, {cateId: 126, cateName: "고등어"}, {cateId: 127, cateName: "오징어"}, {cateId: 128, cateName: "조기"}]
    ];


    var dropDown = function () {
        var cateHoverDiv = document.querySelector(".cate_hover_div");
        cateHoverDiv.style.display = "block";
    };

    var dropUp = function () {
        var cateHoverDiv = document.querySelector(".cate_hover_div");

        cateHoverDiv.style.display = "none";
    }
      
    var hoverIn = function () {
        var cateInUl = this.querySelector(".cate_in_ul");
        console.log("this:", this);

        if (cateInUl) {
            cateInUl.style.display = "block";
        }
    };

    var hoverOut = function () {
        var cateInUl = this.querySelector(".cate_in_ul"); 

        if (cateInUl) {
            cateInUl.style.display = "none";
            // document.querySelector(".cate_in_ul").remove();
        }
    };  //hoverOut

    
      
      
    var cate = document.querySelector(".cate");
    cate.addEventListener("mouseover", dropDown);
    cate.addEventListener("mouseout", dropUp);        //cate_hover_ul까지 나옴.
    
    var cateHoverDiv = document.querySelector(".cate_hover_div");
    cateHoverDiv.addEventListener("mouseover", hoverIn);
    cateHoverDiv.addEventListener("mouseout", hoverOut);

    

    //cateHoverLi 선택
    var cateHoverLis = document.querySelectorAll(".cate_hover_li");
    var currentTarget;

    var cateHoverLiSelected = function (e) {
        const index = Array.from(cateHoverLis).indexOf(e.currentTarget);
        console.log("e.currentTarget: ", e.currentTarget);
        console.log("e.target: ", e.target);
        currentTarget = e.currentTarget;
        currentTarget.classList.add("cate_hover_li_selected");
        
        // if(document.querySelector(".cate_in_ul") === null) {
            var ul = document.createElement("ul");
            ul.setAttribute("class", "cate_in_ul nonedeco_li");

            var prevUl = document.querySelector(".cate_in_ul.nonedeco_li");
            console.log("prevUl: ", prevUl);
            if(prevUl != null) {
                // prevUl.style.display = "none";
                // prevUl.remove();
                prevUl.parentNode.removeChild(prevUl);
            }

            for (let i = 0; i < cateInLiArr[index].length; i++) {
                let li = document.createElement("li");
                li.setAttribute("class", "cate_in_li");
                li.innerHTML = `<a class="cate_in_li_a" href="/board?name=${cateInLiArr[index][i].cateId}">${cateInLiArr[index][i].cateName}</a>`;
                // li = `<li class="cate_in_li"><a class = "cate_in_li_a" href="/board?name=${cateInLiArr[index][i].cateId}">${cateInLiArr[index][i].cateName}</a></li>`;

                ul.appendChild(li);        
            } //for

           
            console.log("ul: ", ul);
            // e.currentTarget.append(ul);
            var hoverText = document.querySelectorAll(".cate_hover_li_text");
            hoverText[index].append(ul);
        // }



        var prevLi = document.querySelector(".cate_in_li_selected");
        if (prevLi) {
            prevLi.classList.remove("cate_in_li_selected");
        }

        console.log("e.target: ", e.target);
        console.log("e.target.tagName: ", e.target.tagName);
        if(e.target.tagName === 'A') {
            e.target.classList.add("cate_in_li_selected");
            // e.target.classList.add("cate_in_li_selected");
        }
        
    };
    
    var cateHoverLiDeselected = function (e) {
        // const index = Array.from(cateHoverLis).indexOf(e.currentTarget);
        console.log("e.currentTarget: ", e.currentTarget);
        e.currentTarget.classList.remove("cate_hover_li_selected");

        let cateInLi = document.querySelectorAll(".cate_in_li");
        // cateInLi.forEach((a) => {
        //     a.remove();
        // });
    };

    cateHoverLis.forEach((li) => {
        li.addEventListener('mouseover', cateHoverLiSelected);
        li.addEventListener('mouseout', cateHoverLiDeselected);
    });

    // var inHover = function (e) {
    //     console.log(e.currentTarget);
    // }

    // var outHover = function (e) {
    //     console.log(e.currentTarget);
    // }

    // var cateInUL = document.querySelectorAll(".cate_in_ul");
    // cateInUL.addEventListener('mouseover', inHover);
    // cateInUL.addEventListener('mouseout', outHover);

    // var cateInLi = document.querySelectorAll(".cate_in_li");
    // cateInLi.forEach((li) => {
    //     li.addEventListener('mouseover', function (e) {
    //        e.target.classList.add("cate_in_li_selected");
    //     });
    // });
    // cateInLi.forEach((li) => {
    //     li.addEventListener('mouseout', function (e) {
    //         e.target.classList.remove("cate_in_li_selected");
    //     });
    // });
    document.querySelector('.cate_in_li_a').addEventListener('click', function(event) {
        event.preventDefault();
        window.location.href = this.getAttribute('href');
      });
};




