/*
	LicencovĂˇno pod MIT LicencĂ­, jejĂ­ celĂ˝ text je uveden v souboru licence.txt
	Licenced under the MIT Licence, complete text is available in licence.txt file
*/

/**
 * @overview window
 * @version 2.0
 * @author zara
*/   

/**
 * @class Okenko se stinem, neboli prosta tabulka s deviti prvky
 * @group jak-widgets
 */
JAK.Window = JAK.ClassMaker.makeClass({
	NAME: "Window",
	VERSION: "2.0"
});

/**
 * @param {object} [optObj] Konfiguracni objekt
 * @param {string} [optObj.imagePath="/img/shadow-"] Cesta k obrazkum
 * @param {string} [optObj.imageFormat="png"] Pripona obrazku (png/gif/jpg)
 * @param {int[]} [optObj.sizes=[6,6,6,6]] Pole ctyr velikosti okraju, dle hodinovych rucicek
 */
JAK.Window.prototype.$constructor = function(optObj) {
	this.options = {
		imagePath:"/img/shadow-",
		imageFormat:"png",
		sizes:[6,6,6,6]
	}
	for (var p in optObj) { this.options[p] = optObj[p]; }

	/**
	 * @field content vnitrni bunka, do ktere se da pridavat dalsi obsah
	 */
	this.content = JAK.mel("div", {className:"window-content"}, {position:"relative"});;
	/**
	 * @field vnejsi prvek
	 */
	this.container = false;
	this._buildDom();
}

/**
 * Tvorba DOM stromu
 */
JAK.Window.prototype._buildDom = function() {
	var imageNames = [
		["lt","t","rt"],
		["l","","r"],
		["lb","b","rb"]
	]
	this.container = JAK.mel("div", {className:"window-container"}, {position:"relative",zIndex:10});
	var table = JAK.mel("table", null, {borderCollapse:"collapse",position:"relative"});
	var tbody = JAK.mel("tbody");
	JAK.DOM.append([table,tbody],[this.container,table]);
	
	for (var i=0;i<3;i++) {
		var tr = JAK.cel("tr");
		tbody.appendChild(tr);
		for (var j=0;j<3;j++) {
			var td = JAK.cel("td");
			td.style.padding = "0px";
			td.style.margin = "0px";
			var div = (i == 1 && j == 1 ? this.content : JAK.mel("div", null, {overflow:"hidden"}));
			td.appendChild(div);
			
			var im = imageNames[i][j];
			if (im) { /* image */
				var path = this.options.imagePath + im + "." + this.options.imageFormat;
				if (JAK.Browser.client == "ie" && JAK.Browser.version < 7 && this.options.imageFormat.match(/png/i)) {
					td.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+path+"',sizingMethod='scale')";
				} else {
					td.style.backgroundImage = "url("+path+")";
				} /* not ie */
			}
			
			/* dimensions */
			if (i == 0) { div.style.height = this.options.sizes[0]+"px"; }
			if (i == 2) { div.style.height = this.options.sizes[2]+"px"; }
			if (j == 0) { div.style.width = this.options.sizes[3]+"px"; }
			if (j == 2) { div.style.width = this.options.sizes[1]+"px"; }
			if (j == 1 && i != 1) { td.style.width = "auto"; }
			
			tr.appendChild(td);
		} /* for all columns */
	} /* for all rows */
}

/**
 * @method Explicitni desktruktor. Smaze vsechny vlastnosti.
 */
JAK.Window.prototype.$destructor = function() {
	for (var p in this) { this[p] = null; }
}

/**
 * Ukazani okna
 */
JAK.Window.prototype.show = function() {
	//this.container.style.visibility = "visible";
	this.container.style.display = "";
}

/**
 * Schovani okna
 */
JAK.Window.prototype.hide = function() {
	//this.container.style.visibility = "hidden";
	this.container.style.display = "none";
}

