package utility;

public enum Policy {
	 ebooks ("p_showebook"),
	 tegrity( "p_tegrity"),
	 hints("p_showhints"),
	 categories("p_showlo"),
	 pages("p_showpageref"),
	 points("p_showpoints"),
	 titles("p_showtitle"),
	 checkWork("p_allowchecks"),
	 feedback("p_allowfeedback_question"),
	 feedback_state("feedbackState"),
	 feedback_SHOWING("show"),
	 grading("p_posttest"),
	 grading_none("none"),
	 grading_score("score"),
	 grading_scoreplus("scoreplus"),
	 grading_feedback("feedback"),	
	 solution("p_solution"),
	 palette("p_palette"),
	 indicators("p_indicators"),
	 fillBlankStrict("p_fillblankstrict"),
	 ignoreAccents("p_ignoreAccents"),
	 limit("p_timelimit"),
	 hardlimit("p_hardtimelimit"),
	 hardlimit_flag("hasHardTimeLimit"),
	 printing("p_allowprinting"),
	 preview("p_allowpreview"),
	 attempts("p_attempts"),
	 password("p_password"),
	 difficulty("p_difficulty"),
	 scrambling("p_scrambling"),
	 EZTO_tolerance("p_eztotolerance"),
	 PERCENT_tolerance("p_tolerancepercent"),
	 qsi("p_qsitutors"),
	 tutorials("p_tutorials"),
	 chat("p_chat"),
	 CONNECT_askinstructor("p_askinstructor"),
	 LSI("p_lsi"),
	 LSI_askinstructor("p_instructor"),
	 LSI_instructoremail("p_instructor_email"),
	 LSI_studentemail("p_student_email"),
	 LSI_subjectemail("p_subject_email"),
	 LSI_chapter("p_chapter"),
	 LSI_section("p_section"),
	 LSI_guidance("p_guidance"),
	 LSI_practice("p_practice"),
	 LSI_netcalc("p_netcalc"),
	 LSI_extcontent("p_content"),
	 LSI_formula("p_formula"),
	 LSI_showanswer("p_showanswer"),
	 LSI_showmywork("p_showmywork"),
	 LSI_linktotext("p_linktotext"),
	 LSI_asknettutor("p_asknettutor"),
	 LSI_attemptOther("p_attempto"),
	 LSI_attemptMulti("p_attemptm"),
	 attachments("p_attachments"),
	 FB_ignorecase("p_fb_ignorecase"),
	 FB_ignoreaccents("p_fb_ignoreaccents"),
	 FB_ignorespacing("p_fb_ignorespacing"),
	 deduct_attempts("p_deduct_attempts"),
	 deduct_hints("p_deduct_hints"),
	 checkwork_limit("p_checkwork_limit"),
	 deduct_checkwork("p_deduct_checkwork"),
	 deduct_resources("p_deduct_resources"),
	 deduct_guidedsolution("p_deduct_guidedsolution"),
	 deduct_showme("p_deduct_showme"),
	 deduct_tryanother("p_deduct_tryanother"),
	 feedback_allcorrect("p_feedback_allcorrect"),
	 endtime("p_endtime"),
	 deduct_late("p_deduct_late"),
	 deduct_late_increment("p_deduct_late_increment"),
	 deduct_late_perHour("hour"),
	 deduct_late_flat("flat"),
	 ACCTTOOL_AUTOCALC("p_acct_autocalc"),
	 ACCTTOOL_NODROPDOWN("p_acct_nodropdown"),
	 ZEUS_CELLATTEMPTS("p_zeus_cellattempts"),
	 ZEUS_FORMULA("p_zeus_formula"),
	 ZEUS_SHOWME("p_zeus_showme"),
	 WIMBA("p_wimba");
	 
	 private String value;
	 private Policy(String value){
		 this.value = value;
	 }
	 
	 public String getValue(){
		 return this.value;
	 }
}