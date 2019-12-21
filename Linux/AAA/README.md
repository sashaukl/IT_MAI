#Недоделанный на первой паре кусок ААА
## попробуйте из под user3 выполнить sudo ls -l /root
user3 is not in the sudoers file.  This incident will be reported.

## почему у вас не получилось?
Команда sudo позволяет обычным пользователям выполнять программы от имени суперпользователя со всеми его правами. Использовать команду sudo могут далеко не все пользователи, а только те, которые указаны в файле /etc/sudoers. Это сообщение об ошибке говорит буквально следующее - вашего пользователя нет в файле sudoers, а значит доступ ему к утилите будет запрещен, а об этом инциденте будет сообщено администратору.

sudo nano /etc/sudoers 
![sq1](https://github.com/sashaukl/IT_MAI/blob/master/Linux/processes/Screenshot%20from%202019-11-20%2019-00-24.png)



![sq2](https://github.com/sashaukl/IT_MAI/blob/master/Linux/processes/cq2.png)
![sq3](https://github.com/sashaukl/IT_MAI/blob/master/Linux/processes/sq3.png)