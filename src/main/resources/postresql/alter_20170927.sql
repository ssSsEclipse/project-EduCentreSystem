alter table tutorial_centre drop column discount_comission_pdf;
alter table tutorial_centre add column discount_comission_pdf bytea;
alter table tutorial_centre add column discount_comission_pdf_filename varchar(512);