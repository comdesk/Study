window.onload = function () {
    const totals = document.querySelectorAll(".total_price");
    const forLength = document.querySelectorAll(".cart_goods_list").length;
    totals.forEach((total) => {
        var tPrice = 0;
        var index = Array.from(totals).indexOf(total);

        for(let i = 1; i <= forLength; i++) {
            const priceClass = `.goods_price${i}`;
            const priceSpans = document.querySelectorAll(priceClass);
            console.log("price:", parseInt(priceSpans[index].textContent.replace(",", "")));
            tPrice += parseInt(priceSpans[index].textContent.replace(",", ""));
        } //for
        
        total.textContent = tPrice.toLocaleString();
    });

    var plusBts = document.querySelectorAll(".count_plus_bt");
    var counts = Array.from(plusBts).map(() => 1);  

    var plus = function (e) {
        var index = Array.from(plusBts).indexOf(e.currentTarget);

        counts[index]++;

        const priceClass = `.goods_price${index+1}`;
        const priceSpans = document.querySelectorAll(priceClass);

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

        const totals = document.querySelectorAll(".total_price");
        const forLength = document.querySelectorAll(".cart_goods_list").length;
        totals.forEach((total) => {
            var tPrice = 0;
            var index = Array.from(totals).indexOf(total);

            for(let i = 1; i <= forLength; i++) {
                const priceClass = `.goods_price${i}`;
                const priceSpans = document.querySelectorAll(priceClass);
                console.log("price:", parseInt(priceSpans[index].textContent.replace(",", "")));
                tPrice += parseInt(priceSpans[index].textContent.replace(",", ""));
            } //for
            
            total.textContent = tPrice.toLocaleString();
        });
    };  //plus

    plusBts.forEach((bt) => {
        bt.addEventListener('click', plus);
    });

    var minusBts = document.querySelectorAll(".count_minus_bt");

    var minus = function (e) {
        console.log(e.target);
        var index = Array.from(minusBts).indexOf(e.currentTarget);

        if(counts[index] > 1) counts[index]--;

        const priceClass = `.goods_price${index+1}`;
        const priceSpans = document.querySelectorAll(priceClass);

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

        const totals = document.querySelectorAll(".total_price");
        const forLength = document.querySelectorAll(".cart_goods_list").length;
        totals.forEach((total) => {
            var tPrice = 0;
            var index = Array.from(totals).indexOf(total);

            for(let i = 1; i <= forLength; i++) {
                const priceClass = `.goods_price${i}`;
                const priceSpans = document.querySelectorAll(priceClass);
                console.log("price:", parseInt(priceSpans[index].textContent.replace(",", "")));
                tPrice += parseInt(priceSpans[index].textContent.replace(",", ""));
            } //for
            
            total.textContent = tPrice.toLocaleString();
        });
    }; //minus

    minusBts.forEach((bt) => {
        bt.addEventListener('click', minus);
    });
    
    var allSelect = document.querySelector("#allSelect");

    if(allSelect.checked) {
        const checkBoxs = document.querySelectorAll(".cart_select");
        checkBoxs.forEach((checkBox) => {
            checkBox.checked = true;
        });
    } //if

    var allDelete = document.querySelector("#allDeletebt");

    allDelete.addEventListener('click', function () {      
        allSelect.checked = false;

        const checkBoxs = document.querySelectorAll(".cart_select");
        checkBoxs.forEach((checkBox) => {
            checkBox.checked = false;
        });
    });

    allSelect.addEventListener('change', function () {
        if(allSelect.checked) {
            const checkBoxs = document.querySelectorAll(".cart_select");
            checkBoxs.forEach((checkBox) => {
                checkBox.checked = true;
            });
        } else {
            const checkBoxs = document.querySelectorAll(".cart_select");
            checkBoxs.forEach((checkBox) => {
                checkBox.checked = false;
            });
        } //if-else
    });    

    
};