# this file simply reads the "cards.json" file and
# inserts a unique integer id

import json

def insert_ids(cards_json):
	cards = []
	card_id = 1
	for card in cards_json["cards"]:
		card["id"] = card_id
		card_id += 1
		cards.append(card)
	return json.dumps(cards)


def main():
	with open("cardscopy.json", "r") as f:
		print(insert_ids(json.load(f)))

main()