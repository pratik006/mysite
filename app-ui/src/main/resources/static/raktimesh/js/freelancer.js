// Freelancer Theme JavaScript
(function(window) {
    var portfolios=new Array();
    $.ajax({
        url: '/raktimesh/data.js',
        async: false,
        success: function(resp) {
            portfolios = JSON.parse(resp);
        }
    });

    function getPortfolios() {
        return portfolios;
    }
    function getPortfolio(index) {
        return portfolios[index];
    }
    window.getPortfolios = getPortfolios;
    window.getPortfolio = getPortfolio;
})(window);


(function($) {
    "use strict"; // Start of use strict

    // jQuery for page scrolling feature - requires jQuery Easing plugin
    $('.page-scroll a').bind('click', function(event) {
        var $anchor = $(this);
        $('html, body').stop().animate({
            scrollTop: ($($anchor.attr('href')).offset().top - 50)
        }, 1250, 'easeInOutExpo');
        event.preventDefault();
    });

    // Highlight the top nav as scrolling occurs
    $('body').scrollspy({
        target: '.navbar-fixed-top',
        offset: 51
    });

    // Closes the Responsive Menu on Menu Item Click
    $('.navbar-collapse ul li a').click(function(){ 
            $('.navbar-toggle:visible').click();
    });

    // Offset for Main Navigation
    $('#mainNav').affix({
        offset: {
            top: 100
        }
    })

    // Floating label headings for the contact form
    $(function() {
        $("body").on("input propertychange", ".floating-label-form-group", function(e) {
            $(this).toggleClass("floating-label-form-group-with-value", !!$(e.target).val());
        }).on("focus", ".floating-label-form-group", function() {
            $(this).addClass("floating-label-form-group-with-focus");
        }).on("blur", ".floating-label-form-group", function() {
            $(this).removeClass("floating-label-form-group-with-focus");
        });


        getPortfolios().forEach(function(item, index) {
            $.ajax({
                url: '/raktimesh/portfolio.html',
                async: false,
                success: function(html) {
                    html = html.replace('a.png', item.cover);
                    html = html.replace("id=\"portfolio\"", "id=\"portfolio"+index+"\"");
                    //html = html.replace("href=\"#portfolioModal\"", "href=\"#portfolioModal"+index+"\"");
                    
                    $('#portfolio').find('.container > :nth-child(2)').append($(html)[0].outerHTML);
                }
            });
        });

    });

    
})(jQuery); // End of use strict


function portfolioClick(obj) {
    var index = obj.id.replace('portfolio', '');
    var portfolio = getPortfolio(index);
    //console.log();
    $('#projectTitle').text(portfolio.name);
    $('#myCarousel > ol').html('');
    $('.carousel-inner').html('');
    portfolio.images.forEach(function(url, i) {
        if (i == 0) {
            $('#myCarousel > ol').append("<li data-target=\'#myCarousel\' data-slide-to=" + i + " class='active'></li>");
            $('.carousel-inner').append("<div class='item active'><img src="+url+" alt='Chania' /></div>");
        } else {
            $('#myCarousel > ol').append("<li data-target=\'#myCarousel\' data-slide-to=" +i+ "></li>");
            $('.carousel-inner').append("<div class='item'><img src="+url+" alt='Chania' /></div>");    
        }
    });
}
