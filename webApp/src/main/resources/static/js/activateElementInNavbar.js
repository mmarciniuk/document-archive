function activateElementInNavbar(elementId) {
    var url = window.location.href;
    console.info("URL = '" + url + "'");
    var navBar = document.getElementById(elementId);

    if (navBar.tagName === "nav") {
        
    } else {
        console.error("Provided element is not a 'nav' tag");
    }

}