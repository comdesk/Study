window.onload = function () {
    //첫화면 토탈
    const totals = document.querySelectorAll(".total_price");
    totals.forEach((total) => {
        var tPrice = 0;
        var index = Array.from(totals).indexOf(total);

        const priceTRs = document.querySelectorAll(".goods_price_tr");
        console.log("index:", index);

        priceTRs.forEach((tr) => {
            const priceSpans = tr.querySelectorAll(".goods_price");
            tPrice += parseInt(priceSpans[index].textContent.replace(",", ""));
        });
        
        total.textContent = tPrice.toLocaleString();
    });

    //플러스버튼 -> 수량 1 증가
    var plusBts = document.querySelectorAll(".count_plus_bt");
    var counts = Array.from(plusBts).map(() => 1);  

    var plus = function (e) {
        var index = Array.from(plusBts).indexOf(e.currentTarget);

        counts[index]++;

        const priceTRs = document.querySelectorAll(".goods_price_tr");
        const priceSpans = priceTRs[index].querySelectorAll(".goods_price");

        priceSpans.forEach((span) => {
            const intialPrice = parseInt(span.dataset.price);
            
            const calPrice = intialPrice * counts[index];
            console.log("calPrice: ", calPrice);

            span.textContent = calPrice.toLocaleString();
        });

        const amountDiv = document.querySelectorAll(".amount_div");
        amountDiv[index].textContent = `${counts[index]}`;

        if (counts[index] >= 2) {
            const minusBt = document.querySelectorAll(".count_minus_bt")[index];
            minusBt.style.backgroundImage = "url(../imgs/minus-xs-svgrepo-black.svg)";
        }

        //합계 구하기
        var totals = document.querySelectorAll(".total_price");
        totals.forEach((total) => {
            var tPrice = 0;
            var index = Array.from(totals).indexOf(total);

            const priceTRs = document.querySelectorAll(".goods_price_tr");
            console.log("index:", index);

            priceTRs.forEach((tr) => {
                const priceSpans = tr.querySelectorAll(".goods_price");
                tPrice += parseInt(priceSpans[index].textContent.replace(",", ""));
            });

            total.textContent = tPrice.toLocaleString();
        });
    };  //plus

    plusBts.forEach((bt) => {
        bt.addEventListener('click', plus);
    });

    //마이너스버튼 -> 수량 1 감소
    var minusBts = document.querySelectorAll(".count_minus_bt");

    var minus = function (e) {
        console.log(e.target);
        var index = Array.from(minusBts).indexOf(e.currentTarget);

        if(counts[index] > 1) counts[index]--;

        const priceTRs = document.querySelectorAll(".goods_price_tr");
        const priceSpans = priceTRs[index].querySelectorAll(".goods_price");

        priceSpans.forEach((span) => {
            const intialPrice = parseInt(span.dataset.price);
            
            const calPrice = intialPrice * counts[index];
            console.log("calPrice: ", calPrice);

            span.textContent = calPrice.toLocaleString();
        });

        const amountDiv = document.querySelectorAll(".amount_div");
        amountDiv[index].textContent = `${counts[index]}`;

        if (counts[index] == 1) {
            e.currentTarget.style.backgroundImage = "url(../imgs/minus-xs-svgrepo-gray.svg)";
        }

        //합계 구하기
        var totals = document.querySelectorAll(".total_price");
        totals.forEach((total) => {
            var tPrice = 0;
            var index = Array.from(totals).indexOf(total);

            const priceTRs = document.querySelectorAll(".goods_price_tr");
            console.log("index:", index);

            priceTRs.forEach((tr) => {
                const priceSpans = tr.querySelectorAll(".goods_price");
                tPrice += parseInt(priceSpans[index].textContent.replace(",", ""));
            });

            total.textContent = tPrice.toLocaleString();
        });
    }; //minus

    minusBts.forEach((bt) => {
        bt.addEventListener('click', minus);
    });

    //상품 개별 삭제
    var deleteBts = document.querySelectorAll(".delete_bt");
    var deleteOne = function (e) {      
        if(confirm('삭제하시겠습니까?')) {
            var tr = e.target.closest(".goods_price_tr");
            tr.remove();
        } //if 

        const totals = document.querySelectorAll(".total_price");
        totals.forEach((total) => {
            var tPrice = 0;
            var index = Array.from(totals).indexOf(total);

            const priceTRs = document.querySelectorAll(".goods_price_tr");
            console.log("index:", index);

            priceTRs.forEach((tr) => {
                const priceSpans = tr.querySelectorAll(".goods_price");
                tPrice += parseInt(priceSpans[index].textContent.replace(",", ""));
            });
            
            total.textContent = tPrice.toLocaleString();
        });
    };

    deleteBts.forEach((bt) => {
        bt.addEventListener('click', deleteOne)
    });
};

