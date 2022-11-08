#!/usr/bin/python3

sql_instruction = 'INSERT INTO cards (id, icons) VALUES '
(file, id) = ("cartas.txt", 0)
with open(file) as cards:
    deck = cards.readlines()
    for card in deck[0:-1]:
        sql_instruction += f'({id}, \'{card.strip()}\'),\n'
        id += 1
    sql_instruction += f'({id}, \'{deck[-1].strip()}\');'
print(f'Deck generated, cards are: {sql_instruction}\n')

with open('../db/hsqldb/cards.sql', 'w+') as f:
    f.write(sql_instruction)
    f.write('\n')
print(f'SQL insert created. Call script to populate')
