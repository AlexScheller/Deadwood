import json

def reparse(board_json):
	ret = {}
	ret["rooms"] = {}
	sets = []
	for movie_set in board_json["board"]["set"]:
		new_set = {}
		new_set["name"] = movie_set["name"]
		# neighbors
		neighbors = []
		for neighbor in movie_set["neighbors"]["neighbor"]:
			neighbors.append(neighbor["name"])
		new_set["neighbors"] = neighbors
		# area
		new_set["area"] = {}
		new_set["area"]["x"] = int(movie_set["area"]["x"])
		new_set["area"]["y"] = int(movie_set["area"]["y"])
		new_set["area"]["h"] = int(movie_set["area"]["h"])
		new_set["area"]["w"] = int(movie_set["area"]["w"])
		# takes
		new_set["takes"] = []
		for take in movie_set["takes"]["take"]:
			# print(take)
			new_take = {}
			new_take["area"] = {}
			new_take["area"]["x"] = int(take["area"]["x"])
			new_take["area"]["y"] = int(take["area"]["y"])
			new_take["area"]["h"] = int(take["area"]["h"])
			new_take["area"]["w"] = int(take["area"]["w"])
			new_take["number"] = int(take["number"])
			new_set["takes"].append(new_take)
		# parts
		new_set["parts"] = []
		for part in movie_set["parts"]["part"]:
			new_part = {}
			new_part["area"] = {}
			new_part["area"]["x"] = int(part["area"]["x"])
			new_part["area"]["y"] = int(part["area"]["y"])
			new_part["area"]["h"] = int(part["area"]["h"])
			new_part["area"]["w"] = int(part["area"]["w"])
			new_part["line"] = part["line"]
			new_part["name"] = part["name"]
			new_part["level"] = int(part["level"])
			new_set["parts"].append(new_part)
		sets.append(new_set)
	ret["rooms"]["sets"] = sets
	# trailer
	trailer = {}
	trailer_neighbors = []
	for neighbor in board_json["board"]["trailer"]["neighbors"]["neighbor"]:
		trailer_neighbors.append(neighbor["name"])
	trailer["neighbors"] = trailer_neighbors
	trailer_area = {}
	trailer_area["x"] = int(board_json["board"]["trailer"]["area"]["x"])
	trailer_area["y"] = int(board_json["board"]["trailer"]["area"]["y"])
	trailer_area["h"] = int(board_json["board"]["trailer"]["area"]["h"])
	trailer_area["w"] = int(board_json["board"]["trailer"]["area"]["w"])
	trailer["area"] = trailer_area
	ret["rooms"]["trailer"] = trailer
	# office
	office = {}
	office_neighbors = []
	for neighbor in board_json["board"]["office"]["neighbors"]["neighbor"]:
		office_neighbors.append(neighbor["name"])
	office["neighbors"] = office_neighbors
	office_area = {}
	office_area["x"] = int(board_json["board"]["office"]["area"]["x"])
	office_area["y"] = int(board_json["board"]["office"]["area"]["y"])
	office_area["h"] = int(board_json["board"]["office"]["area"]["h"])
	office_area["w"] = int(board_json["board"]["office"]["area"]["w"])
	office["area"] = office_area
	upgrades = []
	for upgrade in board_json["board"]["office"]["upgrades"]["upgrade"]:
		new_upgrade = {}
		upgrade_area = {}
		upgrade_area["x"] = int(upgrade["area"]["x"])
		upgrade_area["y"] = int(upgrade["area"]["y"])
		upgrade_area["h"] = int(upgrade["area"]["h"])
		upgrade_area["w"] = int(upgrade["area"]["w"])
		new_upgrade["area"] = upgrade_area
		new_upgrade["level"] = int(upgrade["level"])
		new_upgrade["currency"] = upgrade["currency"]
		new_upgrade["amt"] = upgrade["amt"]
		upgrades.append(new_upgrade)
	office["upgrades"] = upgrades
	ret["rooms"]["office"] = office
	return json.dumps(ret)

def main():
	with open("oldboard.json", "r") as f:
		print(reparse(json.load(f)))

main()