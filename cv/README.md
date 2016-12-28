CV PDF generator
================

Program generujący CV w pdf'ie na podstawie podanego pliku z danym (json). Plik (json) ma określoną strukturę i umożliwa wykorzystanei szeregu komponentów wizualnych uzywanych przy budowie finalnego PDF. Wspomniane komponenty to:

* PLAIN - nagłówek, i text
* LIST  - nagłówek i lista etykieta wartość 
* LIST_WITH_IMAGE - wprowadzająca część CV, nagłówek, lista, zdjęcie
* TABLE_WITH_VERTICAL_DELIMITERS - lista z pionowymi separatorami, uzywana do  pokazania uczestnictwa w projektach
 
W celu uruchomienia projektu, nalezy:
* w resource/application.property - ustawić odpowiednio scieżki do katalogu w którym znajduje się plik z danymi (json)
* przygotować na podstawie przykładowego data.json (swój własny plik z danymi do CV)
* przygotować obrazek (najlepiej zeskalowany, poneiważ w PDF będzie miał wymiar 150x150 pixeli).


CV PDF generator
================

Code can be used for generation of Curriculum Vitae Pdf from data provided in json file. In json data file one need to specify parameters for couple of visual  components used in pdf. Following components are available:

* PLAIN - header and text
* LIST  - header and list of labels and text values 
* LIST_WITH_IMAGE - leading part of CV: header list of label, value pairs and image
* TABLE_WITH_VERTICAL_DELIMITERS - list with vertical delimiters used to visualize membership i projects os employment history.  
  
In order to run the project, you will need:
* w resource/application.property - set appropriate paths to directory containing json data file and image
* prepare json data file which will be base for generated CV. You can help yourself with example data.json (provided in module resources) 
* prepare image. It will be the best to scale it down to lower dimension, because it is used in pdf as 150x150px image (with 72dpi).  
 