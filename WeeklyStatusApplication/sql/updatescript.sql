UPDATE `bct_account` SET `account_owner` = 'Meeza Implementation' WHERE (`account_id` = '17');

UPDATE `bct_dashboard_fields` SET `date_selection_flag` =TRUE, `description_flag` = TRUE, `module_flag` = TRUE, `status_flag` = TRUE, `development_flag` = TRUE WHERE (`project_id` = '1500');
UPDATE `bct_dashboard_fields` SET `remarks_flag` = FALSE, `resource_flag` = TRUE, `account_flag` = TRUE WHERE (`project_id` = '500');
UPDATE `bct_dashboard_fields` SET `member_flag` = TRUE WHERE (`project_id` = '1300');
UPDATE `bct_dashboard_fields` SET `date_selection_flag` = TRUE, `status_flag` = TRUE, `activity_flag` = TRUE WHERE (`project_id` = '1100');
UPDATE `bct_dashboard_fields` SET `resolution_flag` = FALSE, `resource_flag` = TRUE WHERE (`project_id` = '400');
UPDATE `bct_dashboard_fields` SET `created_by_flag` = TRUE, `last_updated_by_flag` = TRUE WHERE (`project_id` = '1700');
UPDATE `bct_dashboard_fields` SET `created_by_flag` = TRUE, `last_updated_by_flag` = TRUE WHERE (`project_id` = '1600');
UPDATE `bct_dashboard_fields` SET `created_by_flag` = TRUE, `last_updated_by_flag` = TRUE WHERE (`project_id` = '1500');
UPDATE `bct_dashboard_fields` SET `created_by_flag` = TRUE, `last_updated_by_flag` = TRUE WHERE (`project_id` = '1400');
UPDATE `bct_dashboard_fields` SET `created_by_flag` = TRUE WHERE (`project_id` = '1100');
UPDATE `bct_dashboard_fields` SET `num_build_sprint_workd_flag` = TRUE, `number_build_sprint_completed_flag` = TRUE, `tests_per_day_flag` = TRUE, `account_flag` = TRUE WHERE (`project_id` = '1200');
UPDATE  `bct_dashboard_fields` SET `num_build_sprint_workd_flag` = TRUE, `number_build_sprint_completed_flag` = TRUE, `remarks_flag` = FALSE, `tests_per_day_flag` = TRUE, `resource_flag` = TRUE, `account_flag` = TRUE WHERE (`project_id` = '500');
UPDATE `bct_dashboard_fields` SET `sub_project_flag` = TRUE, `sub_project_type_flag` = TRUE WHERE (`project_id` = '500');
UPDATE `bct_dashboard_fields` SET `sub_project_flag` = TRUE, `sub_project_type_flag` = TRUE WHERE (`project_id` = '1200');


mysql -u root -p  bctweeklystatus < bctweeklystatus.sql