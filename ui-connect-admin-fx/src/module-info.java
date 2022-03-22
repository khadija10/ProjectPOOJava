module com.app.sysco {
	exports com.app.sysco.app;
	exports com.app.sysco.app.classe;
	exports com.app.sysco.app.cours;
	exports com.app.sysco.app.edt;
	exports com.app.sysco.app.modul;
	exports com.app.sysco.app.chefclasseui;
	exports com.app.sysco.app.admin;
	exports com.app.sysco.app.user;
	exports com.app.sysco.app.assprogarme;
	exports com.app.sysco.app.comptableui;
	
	requires transitive javafx.controls;
	requires java.sql;
}