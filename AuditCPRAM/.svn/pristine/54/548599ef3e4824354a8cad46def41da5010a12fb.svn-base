var mUtil = function() {
    var t = [],
        e = {
            sm: 544,
            md: 768,
            lg: 1024,
            xl: 1200
        },
        a = function() {
            var e = !1;
            window.addEventListener("resize", function() {
                clearTimeout(e), e = setTimeout(function() {
                    ! function() {
                        for (var e = 0; e < t.length; e++) t[e].call()
                    }()
                }, 250)
            })
        };
    return {
        init: function(t) {
            t && t.breakpoints && (e = t.breakpoints), a()
        },
        addResizeHandler: function(e) {
            t.push(e)
        },
        removeResizeHandler: function(e) {
            for (var a = 0; a < t.length; a++) e === t[a] && delete t[a]
        },
        runResizeHandlers: function() {
            _runResizeHandlers()
        },
        resize: function() {
            if ("function" == typeof Event) window.dispatchEvent(new Event("resize"));
            else {
                var t = window.document.createEvent("UIEvents");
                t.initUIEvent("resize", !0, !1, window, 0), window.dispatchEvent(t)
            }
        },
        getURLParam: function(t) {
            var e, a, n = window.location.search.substring(1).split("&");
            for (e = 0; e < n.length; e++)
                if ((a = n[e].split("="))[0] == t) return unescape(a[1]);
            return null
        },
        isMobileDevice: function() {
            return this.getViewPort().width < this.getBreakpoint("lg")
        },
        isDesktopDevice: function() {
            return !mUtil.isMobileDevice()
        },
        getViewPort: function() {
            var t = window,
                e = "inner";
            return "innerWidth" in window || (e = "client", t = document.documentElement || document.body), {
                width: t[e + "Width"],
                height: t[e + "Height"]
            }
        },
        isInResponsiveRange: function(t) {
            var e = this.getViewPort().width;
            return "general" == t || ("desktop" == t && e >= this.getBreakpoint("lg") + 1 || ("tablet" == t && e >= this.getBreakpoint("md") + 1 && e < this.getBreakpoint("lg") || ("mobile" == t && e <= this.getBreakpoint("md") || ("desktop-and-tablet" == t && e >= this.getBreakpoint("md") + 1 || ("tablet-and-mobile" == t && e <= this.getBreakpoint("lg") || "minimal-desktop-and-below" == t && e <= this.getBreakpoint("xl"))))))
        },
        getUniqueID: function(t) {
            return t + Math.floor(Math.random() * (new Date).getTime())
        },
        getBreakpoint: function(t) {
            return e[t]
        },
        isset: function(t, e) {
            var a;
            if (-1 !== (e = e || "").indexOf("[")) throw new Error("Unsupported object path notation.");
            e = e.split(".");
            do {
                if (void 0 === t) return !1;
                if (a = e.shift(), !t.hasOwnProperty(a)) return !1;
                t = t[a]
            } while (e.length);
            return !0
        },
        getHighestZindex: function(t) {
            for (var e, a, n = mUtil.get(t); n && n !== document;) {
                if (("absolute" === (e = mUtil.css(n, "position")) || "relative" === e || "fixed" === e) && (a = parseInt(mUtil.css(n, "z-index")), !isNaN(a) && 0 !== a)) return a;
                n = n.parentNode
            }
            return null
        },
        hasFixedPositionedParent: function(t) {
            for (; t && t !== document;) {
                if (position = mUtil.css(t, "position"), "fixed" === position) return !0;
                t = t.parentNode
            }
            return !1
        },
        sleep: function(t) {
            for (var e = (new Date).getTime(), a = 0; a < 1e7 && !((new Date).getTime() - e > t); a++);
        },
        getRandomInt: function(t, e) {
            return Math.floor(Math.random() * (e - t + 1)) + t
        },
        isAngularVersion: function() {
            return void 0 !== window.Zone
        },
        deepExtend: function(t) {
            t = t || {};
            for (var e = 1; e < arguments.length; e++) {
                var a = arguments[e];
                if (a)
                    for (var n in a) a.hasOwnProperty(n) && ("object" == typeof a[n] ? t[n] = mUtil.deepExtend(t[n], a[n]) : t[n] = a[n])
            }
            return t
        },
        extend: function(t) {
            t = t || {};
            for (var e = 1; e < arguments.length; e++)
                if (arguments[e])
                    for (var a in arguments[e]) arguments[e].hasOwnProperty(a) && (t[a] = arguments[e][a]);
            return t
        },
        get: function(t) {
            var e;
            return t === document ? document : t && 1 === t.nodeType ? t : (e = document.getElementById(t)) ? e : (e = document.getElementsByTagName(t)) ? e[0] : (e = document.getElementsByClassName(t)) ? e[0] : null
        },
        getByClass: function(t) {
            var e;
            return (e = document.getElementsByClassName(t)) ? e[0] : null
        },
        hasClasses: function(t, e) {
            if (t) {
                for (var a = e.split(" "), n = 0; n < a.length; n++)
                    if (0 == mUtil.hasClass(t, mUtil.trim(a[n]))) return !1;
                return !0
            }
        },
        hasClass: function(t, e) {
            if (t) return t.classList ? t.classList.contains(e) : new RegExp("\\b" + e + "\\b").test(t.className)
        },
        addClass: function(t, e) {
            if (t && void 0 !== e) {
                var a = e.split(" ");
                if (t.classList)
                    for (var n = 0; n < a.length; n++) a[n] && a[n].length > 0 && t.classList.add(mUtil.trim(a[n]));
                else if (!mUtil.hasClass(t, e))
                    for (n = 0; n < a.length; n++) t.className += " " + mUtil.trim(a[n])
            }
        },
        removeClass: function(t, e) {
            if (t && void 0 !== e) {
                var a = e.split(" ");
                if (t.classList)
                    for (var n = 0; n < a.length; n++) t.classList.remove(mUtil.trim(a[n]));
                else if (mUtil.hasClass(t, e))
                    for (n = 0; n < a.length; n++) t.className = t.className.replace(new RegExp("\\b" + mUtil.trim(a[n]) + "\\b", "g"), "")
            }
        },
        triggerCustomEvent: function(t, e, a) {
            if (window.CustomEvent) var n = new CustomEvent(e, {
                detail: a
            });
            else(n = document.createEvent("CustomEvent")).initCustomEvent(e, !0, !0, a);
            t.dispatchEvent(n)
        },
        trim: function(t) {
            return t.trim()
        },
        eventTriggered: function(t) {
            return !!t.currentTarget.dataset.triggered || (t.currentTarget.dataset.triggered = !0, !1)
        },
        remove: function(t) {
            t && t.parentNode && t.parentNode.removeChild(t)
        },
        find: function(t, e) {
            if (t = mUtil.get(t)) return t.querySelector(e)
        },
        findAll: function(t, e) {
            if (t = mUtil.get(t)) return t.querySelectorAll(e)
        },
        insertAfter: function(t, e) {
            return e.parentNode.insertBefore(t, e.nextSibling)
        },
        parents: function(t, e) {
            function a(t, e) {
                for (var a = 0, n = t.length; a < n; a++)
                    if (t[a] == e) return !0;
                return !1
            }
            return function(t, e) {
                for (var n = document.querySelectorAll(e), o = t.parentNode; o && !a(n, o);) o = o.parentNode;
                return o
            }(t, e)
        },
        children: function(t, e, a) {
            if (t && t.childNodes) {
                for (var n = [], o = 0, i = t.childNodes.length; o < i; ++o) 1 == t.childNodes[o].nodeType && mUtil.matches(t.childNodes[o], e, a) && n.push(t.childNodes[o]);
                return n
            }
        },
        child: function(t, e, a) {
            var n = mUtil.children(t, e, a);
            return n ? n[0] : null
        },
        matches: function(t, e, a) {
            var n = Element.prototype,
                o = n.matches || n.webkitMatchesSelector || n.mozMatchesSelector || n.msMatchesSelector || function(t) {
                    return -1 !== [].indexOf.call(document.querySelectorAll(t), this)
                };
            return !(!t || !t.tagName) && o.call(t, e)
        },
        data: function(t) {
            return t = mUtil.get(t), {
                set: function(e, a) {
                    void 0 === t.customDataTag && (mUtilElementDataStoreID++, t.customDataTag = mUtilElementDataStoreID), void 0 === mUtilElementDataStore[t.customDataTag] && (mUtilElementDataStore[t.customDataTag] = {}), mUtilElementDataStore[t.customDataTag][e] = a
                },
                get: function(e) {
                    return this.has(e) ? mUtilElementDataStore[t.customDataTag][e] : null
                },
                has: function(e) {
                    return !(!mUtilElementDataStore[t.customDataTag] || !mUtilElementDataStore[t.customDataTag][e])
                },
                remove: function(e) {
                    this.has(e) && delete mUtilElementDataStore[t.customDataTag][e]
                }
            }
        },
        outerWidth: function(t, e) {
            if (!0 === e) {
                var a = parseFloat(t.offsetWidth);
                return a += parseFloat(mUtil.css(t, "margin-left")) + parseFloat(mUtil.css(t, "margin-right")), parseFloat(a)
            }
            return a = parseFloat(t.offsetWidth)
        },
        offset: function(t) {
            var e, a;
            if (t = mUtil.get(t)) return t.getClientRects().length ? (e = t.getBoundingClientRect(), a = t.ownerDocument.defaultView, {
                top: e.top + a.pageYOffset,
                left: e.left + a.pageXOffset
            }) : {
                top: 0,
                left: 0
            }
        },
        height: function(t) {
            return mUtil.css(t, "height")
        },
        visible: function(t) {
            return !(0 === t.offsetWidth && 0 === t.offsetHeight)
        },
        attr: function(t, e, a) {
            if (null != (t = mUtil.get(t))) return void 0 === a ? t.getAttribute(e) : void t.setAttribute(e, a)
        },
        hasAttr: function(t, e) {
            if (null != (t = mUtil.get(t))) return !!t.getAttribute(e)
        },
        removeAttr: function(t, e) {
            null != (t = mUtil.get(t)) && t.removeAttribute(e)
        },
        animate: function(t, e, a, n, o, i) {
            var l = {};
            if (l.linear = function(t, e, a, n) {
                    return a * t / n + e
                }, o = l.linear, "number" == typeof t && "number" == typeof e && "number" == typeof a && "function" == typeof n) {
                "function" != typeof i && (i = function() {});
                var r = window.requestAnimationFrame || function(t) {
                        window.setTimeout(t, 20)
                    },
                    s = e - t;
                n(t);
                var d = window.performance && window.performance.now ? window.performance.now() : +new Date;
                r(function l(c) {
                    var m = (c || +new Date) - d;
                    m >= 0 && n(o(m, t, s, a)), m >= 0 && m >= a ? (n(e), i()) : r(l)
                })
            }
        },
        actualCss: function(t, e, a) {
            var n;
            if (t instanceof HTMLElement != !1) return t.getAttribute("m-hidden-" + e) && !1 !== a ? parseFloat(t.getAttribute("m-hidden-" + e)) : (t.style.cssText = "position: absolute; visibility: hidden; display: block;", "width" == e ? n = t.offsetWidth : "height" == e && (n = t.offsetHeight), t.style.cssText = "", t.setAttribute("m-hidden-" + e, n), parseFloat(n))
        },
        actualHeight: function(t, e) {
            return mUtil.actualCss(t, "height", e)
        },
        actualWidth: function(t, e) {
            return mUtil.actualCss(t, "width", e)
        },
        getScroll: function(t, e) {
            return e = "scroll" + e, t == window || t == document ? self["scrollTop" == e ? "pageYOffset" : "pageXOffset"] || browserSupportsBoxModel && document.documentElement[e] || document.body[e] : t[e]
        },
        css: function(t, e, a) {
            if (t = mUtil.get(t))
                if (void 0 !== a) t.style[e] = a;
                else {
                    var n = (t.ownerDocument || document).defaultView;
                    if (n && n.getComputedStyle) return e = e.replace(/([A-Z])/g, "-$1").toLowerCase(), n.getComputedStyle(t, null).getPropertyValue(e);
                    if (t.currentStyle) return e = e.replace(/\-(\w)/g, function(t, e) {
                        return e.toUpperCase()
                    }), a = t.currentStyle[e], /^\d+(em|pt|%|ex)?$/i.test(a) ? function(e) {
                        var a = t.style.left,
                            n = t.runtimeStyle.left;
                        return t.runtimeStyle.left = t.currentStyle.left, t.style.left = e || 0, e = t.style.pixelLeft + "px", t.style.left = a, t.runtimeStyle.left = n, e
                    }(a) : a
                }
        },
        slide: function(t, e, a, n, o) {
            if (!(!t || "up" == e && !1 === mUtil.visible(t) || "down" == e && !0 === mUtil.visible(t))) {
                a = a || 600;
                var i = mUtil.actualHeight(t),
                    l = !1,
                    r = !1;
                mUtil.css(t, "padding-top") && !0 !== mUtil.data(t).has("slide-padding-top") && mUtil.data(t).set("slide-padding-top", mUtil.css(t, "padding-top")), mUtil.css(t, "padding-bottom") && !0 !== mUtil.data(t).has("slide-padding-bottom") && mUtil.data(t).set("slide-padding-bottom", mUtil.css(t, "padding-bottom")), mUtil.data(t).has("slide-padding-top") && (l = parseInt(mUtil.data(t).get("slide-padding-top"))), mUtil.data(t).has("slide-padding-bottom") && (r = parseInt(mUtil.data(t).get("slide-padding-bottom"))), "up" == e ? (t.style.cssText = "display: block; overflow: hidden;", l && mUtil.animate(0, l, a, function(e) {
                    t.style.paddingTop = l - e + "px"
                }, "linear"), r && mUtil.animate(0, r, a, function(e) {
                    t.style.paddingBottom = r - e + "px"
                }, "linear"), mUtil.animate(0, i, a, function(e) {
                    t.style.height = i - e + "px"
                }, "linear", function() {
                    n(), t.style.height = "", t.style.display = "none"
                })) : "down" == e && (t.style.cssText = "display: block; overflow: hidden;", l && mUtil.animate(0, l, a, function(e) {
                    t.style.paddingTop = e + "px"
                }, "linear", function() {
                    t.style.paddingTop = ""
                }), r && mUtil.animate(0, r, a, function(e) {
                    t.style.paddingBottom = e + "px"
                }, "linear", function() {
                    t.style.paddingBottom = ""
                }), mUtil.animate(0, i, a, function(e) {
                    t.style.height = e + "px"
                }, "linear", function() {
                    n(), t.style.height = "", t.style.display = "", t.style.overflow = ""
                }))
            }
        },
        slideUp: function(t, e, a) {
            mUtil.slide(t, "up", e, a)
        },
        slideDown: function(t, e, a) {
            mUtil.slide(t, "down", e, a)
        },
        show: function(t, e) {
            t.style.display = e || "block"
        },
        hide: function(t) {
            t.style.display = "none"
        },
        addEvent: function(t, e, a, n) {
            void 0 !== (t = mUtil.get(t)) && t.addEventListener(e, a)
        },
        removeEvent: function(t, e, a) {
            (t = mUtil.get(t)).removeEventListener(e, a)
        },
        on: function(t, e, a, n) {
            if (e) {
                var o = mUtil.getUniqueID("event");
                return mUtilDelegatedEventHandlers[o] = function(a) {
                    for (var o = t.querySelectorAll(e), i = a.target; i && i !== t;) {
                        for (var l = 0, r = o.length; l < r; l++) i === o[l] && n.call(i, a);
                        i = i.parentNode
                    }
                }, mUtil.addEvent(t, a, mUtilDelegatedEventHandlers[o]), o
            }
        },
        off: function(t, e, a) {
            t && mUtilDelegatedEventHandlers[a] && (mUtil.removeEvent(t, e, mUtilDelegatedEventHandlers[a]), delete mUtilDelegatedEventHandlers[a])
        },
        one: function(t, e, a) {
            (t = mUtil.get(t)).addEventListener(e, function(t) {
                return t.target.removeEventListener(t.type, arguments.callee), a(t)
            })
        },
        hash: function(t) {
            var e, a = 0;
            if (0 === t.length) return a;
            for (e = 0; e < t.length; e++) a = (a << 5) - a + t.charCodeAt(e), a |= 0;
            return a
        },
        animateClass: function(t, e, a) {
            mUtil.addClass(t, "animated " + e), mUtil.one(t, "webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend", function() {
                mUtil.removeClass(t, "animated " + e)
            }), a && mUtil.one(t.animationEnd, a)
        },
        animateDelay: function(t, e) {
            for (var a = ["webkit-", "moz-", "ms-", "o-", ""], n = 0; n < a.length; n++) mUtil.css(t, a[n] + "animation-delay", e)
        },
        animateDuration: function(t, e) {
            for (var a = ["webkit-", "moz-", "ms-", "o-", ""], n = 0; n < a.length; n++) mUtil.css(t, a[n] + "animation-duration", e)
        },
        scrollTo: function(t, e, a) {
            a = a || 500;
            var n, o, i = (t = mUtil.get(t)) ? mUtil.offset(t).top : 0,
                l = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop || 0;
            i > l ? (n = i, o = l) : (n = l, o = i), e && (o += e), mUtil.animate(n, o, a, function(t) {
                document.documentElement.scrollTop = t, document.body.parentNode.scrollTop = t, document.body.scrollTop = t
            })
        },
        scrollTop: function(t, e) {
            mUtil.scrollTo(null, t, e)
        },
        isArray: function(t) {
            return t && Array.isArray(t)
        },
        ready: function(t) {
            (document.attachEvent ? "complete" === document.readyState : "loading" !== document.readyState) ? t(): document.addEventListener("DOMContentLoaded", t)
        },
        isEmpty: function(t) {
            for (var e in t)
                if (t.hasOwnProperty(e)) return !1;
            return !0
        },
        numberString: function(t) {
            for (var e = (t += "").split("."), a = e[0], n = e.length > 1 ? "." + e[1] : "", o = /(\d+)(\d{3})/; o.test(a);) a = a.replace(o, "$1,$2");
            return a + n
        },
        detectIE: function() {
            var t = window.navigator.userAgent,
                e = t.indexOf("MSIE ");
            if (e > 0) return parseInt(t.substring(e + 5, t.indexOf(".", e)), 10);
            if (t.indexOf("Trident/") > 0) {
                var a = t.indexOf("rv:");
                return parseInt(t.substring(a + 3, t.indexOf(".", a)), 10)
            }
            var n = t.indexOf("Edge/");
            return n > 0 && parseInt(t.substring(n + 5, t.indexOf(".", n)), 10)
        },
        isRTL: function() {
            return "rtl" == mUtil.attr(mUtil.get("html"), "direction")
        },
        scrollerInit: function(t, e) {
            function a() {
                var a, n;
                n = e.height instanceof Function ? parseInt(e.height.call()) : parseInt(e.height), e.disableForMobile && mUtil.isInResponsiveRange("tablet-and-mobile") ? (a = mUtil.data(t).get("ps")) ? (e.resetHeightOnDestroy ? mUtil.css(t, "height", "auto") : (mUtil.css(t, "overflow", "auto"), n > 0 && mUtil.css(t, "height", n + "px")), a.destroy(), a = mUtil.data(t).remove("ps")) : n > 0 && (mUtil.css(t, "overflow", "auto"), mUtil.css(t, "height", n + "px")) : (n > 0 && mUtil.css(t, "height", n + "px"), mUtil.css(t, "overflow", "hidden"), (a = mUtil.data(t).get("ps")) ? a.update() : (mUtil.addClass(t, "m-scroller"), a = new PerfectScrollbar(t, {
                    wheelSpeed: .5,
                    swipeEasing: !0,
                    wheelPropagation: !1,
                    minScrollbarLength: 40,
                    suppressScrollX: !mUtil.isRTL()
                }), mUtil.data(t).set("ps", a)))
            }
            a(), e.handleWindowResize && mUtil.addResizeHandler(function() {
                a()
            })
        },
        scrollerUpdate: function(t) {
            var e;
            (e = mUtil.data(t).get("ps")) && e.update()
        },
        scrollersUpdate: function(t) {
            for (var e = mUtil.findAll(t, ".ps"), a = 0, n = e.length; a < n; a++) mUtil.scrollerUpdate(e[a])
        },
        scrollerTop: function(t) {
            mUtil.data(t).get("ps") && (t.scrollTop = 0)
        },
        scrollerDestroy: function(t) {
            var e;
            (e = mUtil.data(t).get("ps")) && (e.destroy(), e = mUtil.data(t).remove("ps"))
        }
    }
}();
mUtil.ready(function() {
    mUtil.init()
});

var mApp = function() {
    var t = {
            brand: "#716aca",
            metal: "#c4c5d6",
            light: "#ffffff",
            accent: "#00c5dc",
            primary: "#5867dd",
            success: "#34bfa3",
            info: "#36a3f7",
            warning: "#ffb822",
            danger: "#f4516c",
            focus: "#9816f4"
        },
        e = function(t) {
            var e = t.data("skin") ? "m-tooltip--skin-" + t.data("skin") : "",
                a = "auto" == t.data("width") ? "m-tooltop--auto-width" : "",
                n = t.data("trigger") ? t.data("trigger") : "hover";
            t.data("placement") && t.data("placement");
            t.tooltip({
                trigger: n,
                template: '<div class="m-tooltip ' + e + " " + a + ' tooltip" role="tooltip">                <div class="arrow"></div>                <div class="tooltip-inner"></div>            </div>'
            })
        },
        a = function() {
            $('[data-toggle="m-tooltip"]').each(function() {
                e($(this))
            })
        },
        n = function(t) {
            var e = t.data("skin") ? "m-popover--skin-" + t.data("skin") : "",
                a = t.data("trigger") ? t.data("trigger") : "hover";
            t.popover({
                trigger: a,
                template: '            <div class="m-popover ' + e + ' popover" role="tooltip">                <div class="arrow"></div>                <h3 class="popover-header"></h3>                <div class="popover-body"></div>            </div>'
            })
        },
        o = function() {
            $('[data-toggle="m-popover"]').each(function() {
                n($(this))
            })
        },
        i = function(t, e) {
            t = $(t), new mPortlet(t[0], e)
        },
        l = function() {
            $('[m-portlet="true"]').each(function() {
                var t = $(this);
                !0 !== t.data("portlet-initialized") && (i(t, {}), t.data("portlet-initialized", !0))
            })
        },
        r = function() {
            $("[data-tab-target]").each(function() {
                1 != $(this).data("tabs-initialized") && ($(this).click(function(t) {
                    t.preventDefault();
                    var e = $(this),
                        a = e.closest('[data-tabs="true"]'),
                        n = $(a.data("tabs-contents")),
                        o = $(e.data("tab-target"));
                    a.find(".m-tabs__item.m-tabs__item--active").removeClass("m-tabs__item--active"), e.addClass("m-tabs__item--active"), n.find(".m-tabs-content__item.m-tabs-content__item--active").removeClass("m-tabs-content__item--active"), o.addClass("m-tabs-content__item--active")
                }), $(this).data("tabs-initialized", !0))
            })
        };
    return {
        init: function(e) {
            e && e.colors && (t = e.colors), mApp.initComponents()
        },
        initComponents: function() {
            jQuery.event.special.touchstart = {
                setup: function(t, e, a) {
                    "function" == typeof this && (e.includes("noPreventDefault") ? this.addEventListener("touchstart", a, {
                        passive: !1
                    }) : this.addEventListener("touchstart", a, {
                        passive: !0
                    }))
                }
            }, jQuery.event.special.touchmove = {
                setup: function(t, e, a) {
                    "function" == typeof this && (e.includes("noPreventDefault") ? this.addEventListener("touchmove", a, {
                        passive: !1
                    }) : this.addEventListener("touchmove", a, {
                        passive: !0
                    }))
                }
            }, jQuery.event.special.wheel = {
                setup: function(t, e, a) {
                    "function" == typeof this && (e.includes("noPreventDefault") ? this.addEventListener("wheel", a, {
                        passive: !1
                    }) : this.addEventListener("wheel", a, {
                        passive: !0
                    }))
                }
            }, $('[data-scrollable="true"]').each(function() {
                var t = $(this);
                mUtil.scrollerInit(this, {
                    disableForMobile: !0,
                    handleWindowResize: !0,
                    height: function() {
                        return mUtil.isInResponsiveRange("tablet-and-mobile") && t.data("mobile-height") ? t.data("mobile-height") : t.data("height")
                    }
                })
            }), a(), o(), $("body").on("click", "[data-close=alert]", function() {
                $(this).closest(".alert").hide()
            }), l(), $(".custom-file-input").on("change", function() {
                var t = $(this).val();
                $(this).next(".custom-file-label").addClass("selected").html(t)
            }), r()
        },
        initCustomTabs: function() {
            r()
        },
        initTooltips: function() {
            a()
        },
        initTooltip: function(t) {
            e(t)
        },
        initPopovers: function() {
            o()
        },
        initPopover: function(t) {
            n(t)
        },
        initPortlet: function(t, e) {
            i(t, e)
        },
        initPortlets: function() {
            l()
        },
        block: function(t, e) {
            var a, n, o, i = $(t);
            if ("spinner" == (e = $.extend(!0, {
                    opacity: .03,
                    overlayColor: "#000000",
                    state: "brand",
                    type: "loader",
                    size: "lg",
                    centerX: !0,
                    //centerY: !0,
                    centerY: !0,
                    message: "",
                    shadow: !0,
                    width: "auto"
                }, e)).type ? o = '<div class="m-spinner ' + (a = e.skin ? "m-spinner--skin-" + e.skin : "") + " " + (n = e.state ? "m-spinner--" + e.state : "") + '"></div' : (a = e.skin ? "m-loader--skin-" + e.skin : "", n = e.state ? "m-loader--" + e.state : "", size = e.size ? "m-loader--" + e.size : "", o = '<div class="m-loader ' + a + " " + n + " " + size + '"></div'), e.message && e.message.length > 0) {
                var l = "m-blockui " + (!1 === e.shadow ? "m-blockui-no-shadow" : "");
                html = '<div class="' + l + '"><span>' + e.message + "</span><span>" + o + "</span></div>";
               
                i = document.createElement("div");
                mUtil.get("body").prepend(i), mUtil.addClass(i, l), i.innerHTML = "<span>" + e.message + "</span><span>" + o + "</span>", e.width = mUtil.actualWidth(i) + 10, mUtil.remove(i), "body" == t && (html = '<div class="' + l + '" style="margin-left:-' + e.width / 2 + 'px;"><span>' + e.message + "</span><span>" + o + "</span></div>")
            } else html = o;
            var r = {
                message: html,
                centerY: e.centerY,
                centerX: e.centerX,
                //baseZ: 2000000,
                css: {
                    top: "30%",
                    left: "50%",
                    border: "0",
                    padding: "0",
                    backgroundColor: "none",
                    width: e.width
                },
                overlayCSS: {
                    backgroundColor: e.overlayColor,
                    opacity: e.opacity,
                    cursor: "wait",
                    zIndex: "10"
                },
                onUnblock: function() {
                    i && i[0] && (mUtil.css(i[0], "position", ""), mUtil.css(i[0], "zoom", ""))
                }
            };
            "body" == t ? (r.css.top = "50%", $.blockUI(r)) : (i = $(t)).block(r);
        },
        unblock: function(t) {
            t && "body" != t ? $(t).unblock() : $.unblockUI()
        },
        blockPosition : function(position,t){
        	return mApp.block(position, t)
        },
        blockPage: function(t) {
            return mApp.block("body", t)
        },
        unblockPage: function() {
            return mApp.unblock("body")
        },
        unblockPosition : function(position){
        	return mApp.unblock(position)
        },
        progress: function(t, e) {
            var a = "m-loader m-loader--" + (e && e.skin ? e.skin : "light") + " m-loader--" + (e && e.alignment ? e.alignment : "right") + " m-loader--" + (e && e.size ? "m-spinner--" + e.size : "");
            mApp.unprogress(t), $(t).addClass(a), $(t).data("progress-classes", a)
        },
        unprogress: function(t) {
            $(t).removeClass($(t).data("progress-classes"))
        },
        getColor: function(e) {
            return t[e]
        }
    }
}();


//How To Use

/*


$("#m_blockui_1_1").click(function() {
            mApp.block("#m_blockui_1_content", {}), setTimeout(function() {
                mApp.unblock("#m_blockui_1_content")
            }, 2e3)
        }), $("#m_blockui_1_2").click(function() {
            mApp.block("#m_blockui_1_content", {
                overlayColor: "#000000",
                state: "primary"
            }), setTimeout(function() {
                mApp.unblock("#m_blockui_1_content")
            }, 2e3)
        }), $("#m_blockui_1_3").click(function() {
            mApp.block("#m_blockui_1_content", {
                overlayColor: "#000000",
                type: "loader",
                state: "success",
                size: "lg"
            }), setTimeout(function() {
                mApp.unblock("#m_blockui_1_content")
            }, 2e3)
        }), $("#m_blockui_1_4").click(function() {
            mApp.block("#m_blockui_1_content", {
                overlayColor: "#000000",
                type: "loader",
                state: "success",
                message: "Please wait..."
            }), setTimeout(function() {
                mApp.unblock("#m_blockui_1_content")
            }, 2e3)
        }), $("#m_blockui_1_5").click(function() {
            mApp.block("#m_blockui_1_content", {
                overlayColor: "#000000",
                type: "loader",
                state: "primary",
                message: "Processing..."
            }), setTimeout(function() {
                mApp.unblock("#m_blockui_1_content")
            }, 2e3)
        }), $("#m_blockui_2_1").click(function() {
            mApp.block("#m_blockui_2_portlet", {}), setTimeout(function() {
                mApp.unblock("#m_blockui_2_portlet")
            }, 2e3)
        }), $("#m_blockui_2_2").click(function() {
            mApp.block("#m_blockui_2_portlet", {
                overlayColor: "#000000",
                state: "primary"
            }), setTimeout(function() {
                mApp.unblock("#m_blockui_2_portlet")
            }, 2e3)
        }), $("#m_blockui_2_3").click(function() {
            mApp.block("#m_blockui_2_portlet", {
                overlayColor: "#000000",
                type: "loader",
                state: "success",
                size: "lg"
            }), setTimeout(function() {
                mApp.unblock("#m_blockui_2_portlet")
            }, 2e3)
        }), $("#m_blockui_2_4").click(function() {
            mApp.block("#m_blockui_2_portlet", {
                overlayColor: "#000000",
                type: "loader",
                state: "success",
                message: "Please wait..."
            }), setTimeout(function() {
                mApp.unblock("#m_blockui_2_portlet")
            }, 2e3)
        }), $("#m_blockui_2_5").click(function() {
            mApp.block("#m_blockui_2_portlet", {
                overlayColor: "#000000",
                type: "loader",
                state: "primary",
                message: "Processing..."
            }), setTimeout(function() {
                mApp.unblock("#m_blockui_2_portlet")
            }, 2e3)
        }), $("#m_blockui_3_1").click(function() {
            mApp.blockPage(), setTimeout(function() {
                mApp.unblockPage()
            }, 2e3)
        }), $("#m_blockui_3_2").click(function() {
            mApp.blockPage({
                overlayColor: "#000000",
                state: "primary"
            }), setTimeout(function() {
                mApp.unblockPage()
            }, 2e3)
        }), $("#m_blockui_3_3").click(function() {
            mApp.blockPage({
                overlayColor: "#000000",
                type: "loader",
                state: "success",
                size: "lg"
            }), setTimeout(function() {
                mApp.unblockPage()
            }, 2e3)
        }), $("#m_blockui_3_4").click(function() {
            mApp.blockPage({
                overlayColor: "#000000",
                type: "loader",
                state: "success",
                message: "Please wait..."
            }), setTimeout(function() {
                mApp.unblockPage()
            }, 2e3)
        }), $("#m_blockui_3_5").click(function() {
            mApp.blockPage({
                overlayColor: "#000000",
                type: "loader",
                state: "primary",
                message: "Processing..."
            }), setTimeout(function() {
                mApp.unblockPage()
            }, 2e3)
        }), $("#m_blockui_4_1").click(function() {
            mApp.block("#m_blockui_4_1_modal .modal-content", {}), setTimeout(function() {
                mApp.unblock("#m_blockui_4_1_modal .modal-content")
            }, 2e3)
        }), $("#m_blockui_4_2").click(function() {
            mApp.block("#m_blockui_4_2_modal .modal-content", {
                overlayColor: "#000000",
                state: "primary"
            }), setTimeout(function() {
                mApp.unblock("#m_blockui_4_2_modal .modal-content")
            }, 2e3)
        }), $("#m_blockui_4_3").click(function() {
            mApp.block("#m_blockui_4_3_modal .modal-content", {
                overlayColor: "#000000",
                type: "loader",
                state: "success",
                size: "lg"
            }), setTimeout(function() {
                mApp.unblock("#m_blockui_4_3_modal .modal-content")
            }, 2e3)
        }), $("#m_blockui_4_4").click(function() {
            mApp.block("#m_blockui_4_4_modal .modal-content", {
                overlayColor: "#000000",
                type: "loader",
                state: "success",
                message: "Please wait..."
            }), setTimeout(function() {
                mApp.unblock("#m_blockui_4_4_modal .modal-content")
            }, 2e3)
        }), $("#m_blockui_4_5").click(function() {
            mApp.block("#m_blockui_4_5_modal .modal-content", {
                overlayColor: "#000000",
                type: "loader",
                state: "primary",
                message: "Processing..."
            }), setTimeout(function() {
                mApp.unblock("#m_blockui_4_5_modal .modal-content")
            }, 2e3)
        })






*/