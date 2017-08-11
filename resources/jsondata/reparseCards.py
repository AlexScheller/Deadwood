import json

def reparse(cards_json):
	ret = {}
	cards = []
	for card in cards_json["cards"]["card"]:
		new_card = {}
		# scene
		new_card["scene"] = {}
		new_card["scene"]["number"] = int(card["scene"]["number"])
		new_card["scene"]["text"] = card["scene"]["text"]
		# parts
		new_parts = []
		for part in card["part"]:
			new_part = {}
			new_part["area"] = {}
			new_part["area"]["x"] = int(part["area"]["x"])
			new_part["area"]["y"] = int(part["area"]["y"])
			new_part["area"]["h"] = int(part["area"]["h"])
			new_part["area"]["w"] = int(part["area"]["w"])
			new_part["line"] = part["line"]
			new_part["name"] = part["name"]
			new_part["level"] = int(part["level"])
			new_parts.append(new_part)
		new_card["parts"] = new_parts
		# rest
		new_card["name"] = card["name"]
		new_card["img"] = card["img"]
		new_card["budget"] = int(card["budget"])
		cards.append(new_card)
	ret["cards"] = cards
	return json.dumps(ret)

def main():
	with open("oldcards.json", "r") as f:
		print(reparse(json.load(f)))

main()