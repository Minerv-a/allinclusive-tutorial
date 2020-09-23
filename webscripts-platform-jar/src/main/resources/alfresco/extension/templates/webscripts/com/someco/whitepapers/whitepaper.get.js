<import resource="classpath:alfresco/extension/scripts/rating.js">

//var whitepapers = search.luceneSearch("PATH:\"/app:company_home/cm:Someco/cm:Whitepapers/*\" +TYPE:\"{http://www.someco.com/model/content/1.0}whitepaper\"");
var cmisQuery = {
	query	: "SELECT * from sc:whitepaper", 
	language: "cmis-alfresco"
	};
var whitepapers = search.query(cmisQuery);

if (whitepapers == null || whitepapers.length == 0) {
    status.code = 404;
    status.message = "No whitepapers found";
    status.redirect = true;
} else {
    var whitepaperInfo = new Array();
    for (i = 0; i < whitepapers.length; i++) {
        var whitepaper = new whitepaperEntry(whitepapers[i],
        getRating(whitepapers[i]));
        whitepaperInfo[i] = whitepaper;
    }
    model.whitepapers = whitepaperInfo;
}

function whitepaperEntry(whitepaper, rating) {
    this.whitepaper = whitepaper;
    this.rating = rating;
}
