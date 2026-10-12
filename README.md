# Lambino_StoreUI

# Poké Mart — Java Swing

A desktop recreation of the Pokémon **Poké Mart** buy screen, built from
scratch with Java Swing (no GUI builder). Pick an item, read its description,
and buy it with your in-game money.

## Features
- Simulated **₽ balance** that goes down with each purchase
- **Same-screen buying** — success and "not enough money" messages show inline
- **Per-item "In Bag" count** that updates live as you buy
- **Item selection** highlights the chosen row and shows its description + sprite
- Scrollable item list styled after the original Poké Mart layout

## Files
- `PokeMart.java` — the app (window, layout, buttons, buy logic)
- `Item.java` — data class for one shop item (name, price, description, image, owned count)
- `images/` — item sprites and the window icon

## Run it
From the project root (so the `images/` folder is found):