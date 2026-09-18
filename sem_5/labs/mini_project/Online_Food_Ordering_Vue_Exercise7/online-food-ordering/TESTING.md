# Testing – Online Food Ordering Application (Exercise 7)

All test cases below were executed manually against the running application (`npm run dev`).

| # | Test Case | Action / Input | Expected Result | Status |
|---|---|---|---|---|
| 1 | Home page loads | Navigate to `/` | Home page with title, tagline, and "Browse Food Menu" button is shown | Pass |
| 2 | Home → Food Menu navigation | Click "Browse Food Menu" on Home | Navigates to `/order/menu` and shows the menu | Pass |
| 3 | About page navigation | Click "About" in navbar | Navigates to `/about` and shows the About content | Pass |
| 4 | Order page opens | Click "Order" in navbar | Navigates to `/order/menu` (default nested view) showing sub-nav and menu | Pass |
| 5 | Nested Food Menu route works | Visit `/order/menu` directly | `Order.vue` renders with `FoodMenu.vue` inside its `<router-view>` | Pass |
| 6 | Nested Cart route works | Click "Cart" sub-nav link | `Order.vue` renders with `Cart.vue` inside its `<router-view>` at `/order/cart` | Pass |
| 7 | Pizza Add to Cart | Click "Add to Cart" on Pizza | Pizza appears in cart with quantity 1 | Pass |
| 8 | Burger Add to Cart | Click "Add to Cart" on Burger | Burger appears in cart with quantity 1 | Pass |
| 9 | Pasta Add to Cart | Click "Add to Cart" on Pasta | Pasta appears in cart with quantity 1 | Pass |
| 10 | Coke Add to Cart | Click "Add to Cart" on Coke | Coke appears in cart with quantity 1 | Pass |
| 11 | Adding the same item increases quantity | Click "Add to Cart" on Pizza twice | Pizza quantity becomes 2 (no duplicate row) | Pass |
| 12 | Increase quantity | Click `+` next to an item in Cart | Quantity increases by 1, subtotal and total update | Pass |
| 13 | Decrease quantity | Click `-` next to an item with quantity > 1 | Quantity decreases by 1, subtotal and total update | Pass |
| 14 | Decrease quantity floor | Click `-` when quantity is 1 | Quantity stays at 1 (does not go to 0) | Pass |
| 15 | Remove item | Click "Remove" on a cart item | Item is removed entirely from the cart | Pass |
| 16 | Clear cart | Click "Clear Cart" | All items removed, cart shows empty state | Pass |
| 17 | Total items getter | Add Pizza (x2) and Coke (x1) | Cart heading shows "Cart (3 items)" | Pass |
| 18 | Total bill getter | Pizza x2 (₹250) + Coke x1 (₹80) | Total shown is ₹580 | Pass |
| 19 | Empty cart behavior | Open Cart with no items | Shows "Your cart is empty." with a link to the Food Menu | Pass |

## Sample Verified Scenario
- Added Pizza twice → quantity 2, subtotal ₹500
- Added Coke once → quantity 1, subtotal ₹80
- Cart header: **Cart (3 items)**
- Total shown: **₹580**

This matches the exact expected output described in the assignment.
