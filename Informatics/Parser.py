#!/usr/bin/python3
import sys

# built-in regexp lib
import re
import requests
from bs4 import BeautifulSoup

SCHEDULE_URL = 'http://www.ifmo.ru/ru/schedule/0/{}/raspisanie_zanyatiy.htm'


def parse_schedule(group):
    response = requests.get(SCHEDULE_URL.format(group))
    html = response.text

    bs = BeautifulSoup(html, features='lxml')
    content_block = bs.find('article', class_='content_block')
    if 'Расписание не найдено' in str(content_block):
        print(f'Cannot parse group {group} - schedule not found!')
        return
    
    even = []
    odd = []
    rasp_tabl_days = content_block.find_all('div', class_='rasp_tabl_day')
    for rasp_tabl_day in rasp_tabl_days:
        for row in rasp_tabl_day.find('table').find('tbody').find_all('tr')[:-1]:
            lesson = {  
                'time': str(row.find('td', class_='time').find('span').text),
                'lesson': str(row.find('td', class_='lesson').find('dl').find('dd').text),
                'room': str(row.find('td', class_='room').find('dl').find('dd').text)
            }
            print(lesson)

    scheadule = {
        'even': even,
        'odd': odd
    }
    groupScheadule = {
        'group': group,
        'scheadule': scheadule
    }
    print(groupScheadule)


if __name__ == "__main__":
    if len(sys.argv) < 2:
        print('Usage: sync.py <group 1> <group 2> ... <group N>')

    for group in sys.argv[1:]:
        result = re.match(r'\w\d{4}', group)
        if not result:
            print(f'Cannot parse group {group}, maybe you make mistake?')
            sys.exit(0)

    for group in sys.argv[1:]:
        print(group)
        parse_schedule(group)
