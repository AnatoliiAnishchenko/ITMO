#!/bin/bash
#Lab0 script

#Issue1
mkdir lab0
cd lab0
mkdir nidorina2
cd nidorina2
mkdir wingull
mkdir vulpix
mkdir beheeyem
touch kirlia
touch ampharos
touch palpitoad
echo "Возможности  Overland=7 Surface=4 Jump=2 Power=1" >> kirlia
echo "Intelligence=5 Telekinetic=0 Telepath=0" >> kirlia
echo "Ходы  After You Body" >> ampharos
echo "Slam Counter Defense Curl Double-Edge Dynamicpunch Fire Punch# Focus" >> ampharos
echo "Punch Heal Bell Iron Tail Magnet Rise Mega Kick Mega Punch Outrage" >> ampharos
echo "Seismic Toss Shock Wave Signal Beam Sleep Talk Snore Swift" >> ampharos
echo "Thunderpunch" >> ampharos
echo "satk=7 sdef=6 spd=7" >> palpitoad
cd ..
mkdir steelix6
cd steelix6
mkdir yamask
mkdir diglett
touch seel
touch gastly
touch pawniard
echo "Тип диеты" >> seel
echo "Carnivore" >> seel
echo "Живет  Cave Urban" >> gastly
echo "Ходы  Dark Pulse Dual" >> pawniard
echo "Chop Foul Play Iron Defense Iron Head Knock Off Low Kick Magnet Rise" >> pawniard
echo "Role Play Sleep Talk Snatch Snore Spite Stealth Rock" >> pawniard
cd ..
mkdir treecko4
cd treecko4
mkdir lucario
mkdir krookodile
mkdir petilil
mkdir samurott
cd ..
touch tyrogue4
touch vulpix4
touch zubat8
echo "Тип" >> tyrogue4
echo "диеты  Omnivore" >> tyrogue4
echo "Тип диеты  Herbivore" >> vulpix4
echo "Развитые" >> zubat8
echo "способности  Infiltrator" >> zubat8

#Issue2
chmod 777 nidorina2
cd nidorina2
chmod 513 wingull
chmod 440 kirlia
chmod 333 vulpix
chmod 444 ampharos
chmod 644 palpitoad
chmod 751 beheeyem
cd ..
chmod 764 steelix6
cd steelix6
chmod 044 seel
chmod 764 yamask
chmod 700 diglett
chmod 062 gastly
chmod 404 pawniard
cd ..
chmod 733 treecko4
cd treecko4
chmod 333 lucario
chmod 752 krookodile
chmod 753 petilil
cd ..
chmod 400 tyrogue4
chmod 644 vulpix4
chmod 062 zubat8

#Issue3
cp -r steelix6 treecko4/samurott
#cp: cannot open steelix6/seel: Permission denied
#cp: cannot open steelix6/gastly: Permission denied
cat nidorina2/palpitoad steelix6/gastly >> zubat8_44
ln -s vulpix4 steelix6/pawniardvulpix
ln -s nidorina2 Copy_21
cat zubat8 >> steelix6/seelzubat
cp zubat8 nidorina2/wingull
ln tyrogue4 nidorina2/palpitoadtyrogue

#Issue4
wc -c vulpix4 >> vulpix4 2> /tmp/filename
ls -RF nidorina2 2> /dev/null | grep -v "/" | grep -v "@$" | grep -v ":$" | grep -v "^$" | sort -r
cat zubat8 2> /tmp/filename | sort -r
ls -lR 2>&1 | grep "^-" | sort -k 9 -r | tail -4
cat tyrogue4 2> /dev/null | sort
ls -lR 2> /dev/null | grep "^-" | sort -n -k 7 | head -2
