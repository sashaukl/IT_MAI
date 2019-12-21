# Lab-processes.md
### Какой размер дисков?
один диск 931,5гб

### Есть ли неразмеченное место на дисках?
нет

### Какой размер партиций?
![sq3](https://github.com/sashaukl/IT_MAI/blob/master/Linux/processes/sq4.png)

### Какая таблица партционирования используется?
gpt

### Какой диск, партция или лвм том смонтированы в /
sda4 
![](https://github.com/sashaukl/IT_MAI/blob/master/Linux/processes/sq5.png)

### Создадим сжатую файловую систему для чтения squashfs
git clone https://gitlab.com/erlong15/mai.git
mksquashfs mai/* mai.sqsh
![](https://github.com/sashaukl/IT_MAI/blob/master/Linux/processes/sq6.png)


## Посмотрим информацию по файловым системам смонтированным в системе
### Какая файловая система примонтирована в /
![](https://github.com/sashaukl/IT_MAI/blob/master/Linux/processes/sq7.png)

### С какими опциями примонтирована файловая система в /
mount | grep sda4
/dev/sda4 on / type ext4 (rw,relatime,errors=remount-ro)

### Какой размер файловой системы приментированной в /mnt/mai
/dev/loop8      128K  128K     0 100% /mnt/mai
128K

### Что такое tmpfs?
Tmpfs — временное файловое хранилище

### Какая часть памяти изменялась?
100M
![](https://github.com/sashaukl/IT_MAI/blob/master/Linux/processes/sq8.png)

### Какие процессы в системе порождают дочерние процессы через fork
systemd, gdm3, kthreadd

### Какие процессы в системе являются мультитредовыми
firefox, kthreadd

### Разберитесь что делает команда (ps axo rss | tail -n +2|paste -sd+ | bc)
Cчитыватеn кол-во занятой оперативной пямяти не в swap использованной в процессах всех пользователей странная потому что она в кб

## Уставновим утилиту smem и проанализируем параметр PSS в ней
![](https://github.com/sashaukl/IT_MAI/blob/master/Linux/processes/sq9.png)

## Запустим приложеннный скрипт и понаблюдаем за процессами
### в другом терминале  отследите порождение процессов
![](https://github.com/sashaukl/IT_MAI/blob/master/Linux/processes/sq10.png)
### отследите какие состояния вы видите у процессов
T и Z
### почему появляются процессы со статусам Z
зомби процессы
### какой PID у основного процесса
23009
### убейте основной процесс kill -9 <pid> какой PPID стал у первого чайлда
23009
### насколько вы разобрались в скрипте и втом что он делает?
В ограниченном цикле с делаем штампует новые форки

## Научимся корректно завершать зомби процессы
запустим еще раз наш процесс
убьем процесс первого чайлда
проверим его состояние  и убедимся что он зомби
остановим основной процесс
расскоментируем строки в скрипте
поторим все еще раз
отследим корректное завершение чайлда
![](https://github.com/sashaukl/IT_MAI/blob/master/Linux/processes/sq11.png)

## Научимся убивать зомби процессы
Bye
![](https://github.com/sashaukl/IT_MAI/blob/master/Linux/processes/sq12.png)

##Проблемы при отмонтировании директории
- target is busy
user 18558 ..c.. bash
мешает баш, убиваем по пиду и закрыватся терминал)
команда срабатывает при выходе из папки
device is busy

## Решаем загадку исчезновения места на диске
После удаления файла usage увеличился на 4% мы удалили файл, но процесс который щаписывает в память все еще жив, он и есть память. 

## Утилиты наблюдения
- проверьте текущий LA  load average: 3,37, 2,80, 2,2
cat /proc/loadavg
- запишите top 3 процессов загружающих CPU
 top -b -o +%CPU | head -n 10
- запишите top 3 процессов загружающих память
top -b -o +%MEM | head -n 10
- запустите утилиту atop как сервис через systemd
```
vagrant@ubuntu-xenial:/usr/bin$ sudo nano /etc/systemd/system/atop1.service
vagrant@ubuntu-xenial:/usr/bin$ sudo systemctl enable atop1.service
vagrant@ubuntu-xenial:/usr/bin$ sudo systemctl start atop1.service
\vagrant@ubuntu-xenial:/usr/bin$ sudo systemctl status atop1.service
● atop1.service - AtopService
   Loaded: loaded (/etc/systemd/system/atop1.service; static; vendor preset: enabled)
   Active: active (running) since Sat 2019-12-21 22:24:34 UTC; 7s ago
 Main PID: 3254 (atop)
    Tasks: 1
   Memory: 1.3M
      CPU: 6ms
   CGroup: /system.slice/atop1.service
           └─3254 /usr/bin/atop

Dec 21 22:24:34 ubuntu-xenial atop[3254]:   417   0.00s   0.00s     0K     0K     0K     0K N-   - S   0% kauditd
Dec 21 22:24:34 ubuntu-xenial atop[3254]:   487   0.00s   0.00s     0K     0K     0K     0K N-   - S   0% iprt-VBoxWQueu
Dec 21 22:24:34 ubuntu-xenial atop[3254]:  2113   0.00s   0.00s     0K     0K     0K     0K N-   - S   0% kworker/0:2
Dec 21 22:24:34 ubuntu-xenial atop[3254]:  3073   0.00s   0.00s     0K     0K     0K     0K N-   - S   0% kworker/1:0
Dec 21 22:24:34 ubuntu-xenial atop[3254]:  3133   0.00s   0.00s     0K     0K     0K     0K N-   - S   0% kworker/1:2
Dec 21 22:24:34 ubuntu-xenial atop[3254]:  3251   0.00s   0.00s     0K     0K     0K     0K N-   - Z   0% sudo
Dec 21 22:24:34 ubuntu-xenial atop[3254]:  3255   0.00s   0.00s     0K     0K     0K     0K N-   - S   0% kworker/0:0
Dec 21 22:24:34 ubuntu-xenial atop[3254]:  3253   0.00s   0.00s     0K     0K      -      - NE   0 E   0% <systemd-tty->
Dec 21 22:24:34 ubuntu-xenial atop[3254]:  3252   0.00s   0.00s     0K     0K      -      - NE   0 E   0% <systemctl>
Dec 21 22:24:34 ubuntu-xenial atop[3254]:  3251   0.00s   0.00s     0K     0K      -      - -E   0 E   0% <sudo>

```
- запустите dd на генерацию файла размер в 3 гигабайта
```
vagrant@ubuntu-xenial:/usr/bin$ sudo dd of=file bs=1 count=0 seek=3G
0+0 records in
0+0 records out
0 bytes copied, 0.00047458 s, 0.0 kB/s
```
- удалите сгенеренный файл
```
vagrant@ubuntu-xenial:~$ rm file
rm: remove write-protected regular file 'file'? y
```
