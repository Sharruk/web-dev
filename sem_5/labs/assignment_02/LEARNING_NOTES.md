# 📘 LEARNING NOTES — TechNova 2026 Web Development Assignment

> **Semester 5 | Web Development Lab | Assignment 02**
> SSN College of Engineering | Department of Computer Science and Engineering

---

## 📋 TABLE OF CONTENTS

1. [Simple Explanation of Every HTML Page](#1-simple-explanation-of-every-html-page)
2. [CSS Concepts Used](#2-css-concepts-used)
3. [Common Viva Questions with Short Answers](#3-common-viva-questions-with-short-answers)
4. [Frequently Asked Interview / Lab Questions](#4-frequently-asked-interview--lab-questions)
5. [Important HTML Tags Used](#5-important-html-tags-used)
6. [Important CSS Properties Used](#6-important-css-properties-used)
7. [Common Mistakes to Avoid](#7-common-mistakes-to-avoid)
8. [One-Page Quick Revision Sheet](#8-one-page-quick-revision-sheet)
9. [5-Minute Revision Before Lab Exam](#9-5-minute-revision-before-lab-exam)

---

## 1. Simple Explanation of Every HTML Page

### 📄 index.html — Home Page
**What it shows:** The landing page of the TechNova 2026 website.
**Contains:**
- A college logo and event title in the `<header>`.
- A sticky navigation bar using `<nav>` with links to all pages.
- A large banner image displaying the event poster.
- An overview section (`<section>`) with the event theme, date, and venue.
- A "highlights" section showing stats (500+ participants, 15 events, ₹50K prizes, 5+ experts) using **Flexbox** to arrange cards in a row.
- A footer with contact info, quick links, and social media.

**Key concepts shown:** Flexbox (highlights + nav + footer), Sticky Position, Hover + Transition, Box Shadow, Text Shadow, Pseudo-element `::before`, ID selector (`#symposium-overview`).

---

### 📄 about.html — About Page
**What it shows:** Information about the symposium — objectives, vision, mission, organizing department, and benefits.
**Contains:**
- Unordered list `<ul>` for objectives (bullet points).
- Ordered list `<ol>` for numbered "Why Participate?" points.
- `<div class="vision-block">` for a styled left-accent info box.
- `<div class="info-bar">` for a highlighted department description.
- `<span class="badge">` — small pill-shaped labels using `display: inline-block`.

**Key concepts shown:** `<ul>` vs `<ol>`, `<li>`, `<b>` tag, `display: inline-block` (badge), `display: block` (info-bar), `display: inline` (text-inline), border-left accent.

---

### 📄 technical.html — Technical Events Page
**What it shows:** Details of 4 technical events: Paper Presentation, Web Design Contest, Coding Challenge, Project Expo.
**Contains:**
- A **CSS Grid** layout (`display: grid`) to arrange 4 event cards in 2 columns × 2 rows.
- Each event uses an `<article>` semantic tag (instead of `<div>`).
- Each card has an image, description, rules list, and prize table.
- **HTML Tables** inside each card for prize information.

**Key concepts shown:** CSS Grid (`grid-template-columns: repeat(2, 1fr)`), `<article>` semantic tag, `<table>`, `<thead>`, `<tbody>`, `<tr>`, `<th>`, `<td>`, `&amp;` HTML entity.

---

### 📄 nontechnical.html — Non-Technical Events Page
**What it shows:** Details of 4 non-technical events: Photography, Quiz Competition, Treasure Hunt, Gaming Tournament.
**Contains:**
- Same **CSS Grid** layout as technical.html (2-column grid).
- Each event in an `<article>` with rules, registration fee, and prize table.
- Shows consistent use of `:nth-child(odd/even)` for table zebra striping.

**Key concepts shown:** CSS Grid, `<article>`, `<table>` with `<thead>`/`<tbody>`, `:nth-child`, `:hover` on rows, `<ul>` for rules.

---

### 📄 schedule.html — Schedule Page
**What it shows:** The complete day schedule as a large HTML table.
**Contains:**
- A large table with 13 rows showing time, event name, and venue.
- `<thead>` (header row) and `<tbody>` (data rows) for proper structure.
- `<div class="info-bar">` for date and venue info box.
- Alternating row colors using `:nth-child(odd/even)`.
- Important notes section using `<ul>`.

**Key concepts shown:** HTML Tables (full structure), `border-collapse: collapse`, `thead`, `tbody`, `th` vs `td`, zebra striping, row hover, `:nth-child`.

---

### 📄 register.html — Registration Form Page
**What it shows:** A registration form for students to sign up for TechNova events.
**Contains:**
- `<form action="#" method="post">` — the form wrapper.
- 3 `<input type="text">` fields: full name, college, department.
- 2 `<select>` dropdowns: year, and two event dropdowns.
- 2 `<input type="email">` and `type="tel"` for contact info.
- 2 `<textarea>` fields: address and comments.
- `<input type="submit">` and `<input type="reset">` buttons.
- `:focus` effect with blue border and glow on focused inputs.

**Key concepts shown:** HTML Forms, all input types, `<label>` + `for` attribute, `<select>` + `<option>`, `<textarea>`, `:focus` pseudo-class, `type="submit"` and `type="reset"`.

---

### 📄 contact.html — Contact Page
**What it shows:** Contact details for the faculty and student coordinators, plus the venue address and a map image.
**Contains:**
- **Flexbox** layout (`contact-flex`) to arrange 3 coordinator cards side by side.
- Each card is a `<div class="contact-block">` with name, email/phone.
- `<b>` tag for bold labels like "Name:", "Email:".
- `<br>` tag for line breaks inside the address block.
- A responsive map image (`<img class="map-img">`).

**Key concepts shown:** Flexbox for contact layout, `<b>` tag, `<br>` tag, `display: block` on image for centering, Box Shadow on map image.

---

## 2. CSS Concepts Used

| Concept | Description | Where Used |
|---|---|---|
| **Element Selector** | Targets HTML tags directly (`body`, `nav`, `footer`) | All global styles |
| **Class Selector** | Targets elements with a class attribute (`.nav-label`) | Throughout all sections |
| **ID Selector** | Targets one unique element (`#symposium-overview`) | index.html overview section |
| **Descendant Selector** | Targets elements inside another (`nav a`, `footer h4`) | Nav links, footer links |
| **Attribute Selector** | Targets by attribute value (`input[type="text"]`) | Form inputs |
| **Pseudo-class :hover** | Applies when mouse is over element | Nav links, cards, buttons, rows |
| **Pseudo-class :focus** | Applies when input is clicked/tabbed | Form inputs |
| **Pseudo-class :nth-child** | Targets by position in parent | Table row alternating colors |
| **Pseudo-element ::before** | Inserts content before element | Footer © symbol |
| **Flexbox** | 1D row/column layout | Nav, highlights, footer, contact |
| **CSS Grid** | 2D row+column layout | Event cards (technical, nontechnical) |
| **Sticky Position** | Sticks to top on scroll | Navigation bar |
| **Box Shadow** | Shadow around elements | Cards, logo, images, map |
| **Text Shadow** | Shadow behind text | Page headings |
| **Transition** | Smooth animation between states | Hover effects everywhere |
| **Transform** | Move/scale elements | Highlight card lift, button scale |
| **Media Queries** | Responsive layout changes | Tablet + mobile breakpoints |
| **Box Model** | Content + Padding + Border + Margin | `box-sizing: border-box` |
| **display: block** | Full-width element | info-bar, images, form inputs |
| **display: inline** | Stays in text flow | text-inline span labels |
| **display: inline-block** | Inline but accepts padding | badge, nav-label, buttons |

---

## 3. Common Viva Questions with Short Answers

**Q1: What is the difference between `<div>` and `<section>`?**
Both are block-level containers, but `<section>` is a semantic tag — it has meaning (it tells the browser this is a logical section of content). `<div>` has no semantic meaning and is used for styling/layout purposes only.

---

**Q2: Why is `<article>` used for event cards instead of `<div>`?**
`<article>` is a semantic HTML5 tag for self-contained content that makes sense on its own (like a news article or event card). It helps search engines and screen readers understand the content structure. `<div>` would work but is not semantic.

---

**Q3: What does `DOCTYPE html` do?**
It tells the browser that this document is written in HTML5. Without it, the browser may use "quirks mode" and render the page incorrectly.

---

**Q4: What is the difference between `id` and `class`?**
- `id` is unique — only ONE element per page can have a given id. Selected with `#` in CSS.
- `class` is reusable — multiple elements can share the same class. Selected with `.` in CSS.

---

**Q5: What is Flexbox and when do you use it?**
Flexbox (`display: flex`) is a CSS layout model that arranges items in a single row or column. Use it for navigation bars, equal-width columns, centering content, and footers — any single-direction layout.

---

**Q6: What is CSS Grid and how is it different from Flexbox?**
CSS Grid (`display: grid`) is a 2-dimensional layout system (controls both rows AND columns). Flexbox is 1D (either row OR column). Use Grid when you need to control both axes at once — like the event cards page with 2 rows × 2 columns.

---

**Q7: What is `position: sticky`?**
`position: sticky` makes an element scroll normally until it reaches a defined position (like `top: 0`), then it "sticks" there. Unlike `position: fixed`, sticky still takes up space in the page layout.

---

**Q8: What is a pseudo-class? Give an example.**
A pseudo-class is a keyword added to a selector that specifies a special state of the element. Example: `nav a:hover` applies styles only when the mouse hovers over a nav link. Other examples: `:focus`, `:nth-child()`, `:active`.

---

**Q9: What is a pseudo-element? Give an example.**
A pseudo-element creates a virtual element (not in HTML) that can be styled. Example: `.footer-bottom::before { content: "© "; }` inserts the © symbol before the footer text without modifying the HTML.

---

**Q10: What is `border-collapse: collapse` in a table?**
By default, each table cell has its own border, creating double lines between cells. `border-collapse: collapse` merges adjacent borders into a single clean line.

---

**Q11: What is the difference between `<th>` and `<td>`?**
`<th>` (table header) is used for column/row labels — bold by default and semantically marks header data. `<td>` (table data) is used for regular data cells in the table body.

---

**Q12: What does `alt` in an `<img>` tag do?**
The `alt` attribute provides alternative text shown when the image fails to load. It's also read aloud by screen readers for visually impaired users, and used by search engines.

---

**Q13: What is the difference between `<ul>` and `<ol>`?**
`<ul>` (unordered list) creates bullet points — use when order doesn't matter.
`<ol>` (ordered list) creates numbered items — use when order matters.

---

**Q14: What does `box-sizing: border-box` do?**
It changes how CSS calculates width/height. Without it, padding and border are added ON TOP of the declared width. With `border-box`, the declared width INCLUDES padding and border — making sizing more predictable.

---

**Q15: What is `transition` in CSS?**
`transition` makes property changes animate smoothly over a specified time. Example: `transition: background-color 0.3s;` makes the background color change gradually over 0.3 seconds instead of changing instantly.

---

**Q16: What does `display: inline-block` do?**
It combines inline behavior (sits next to text, no forced new line) with block behavior (accepts padding, width, height). The `.badge` class uses it so the pill label sits next to list text but can still have padding.

---

**Q17: Why do we use `margin: 0 auto` for centering?**
`margin: auto` on left and right sides tells the browser to distribute equal space on both sides, centering the element. This only works for block-level elements with a defined width (or max-width).

---

**Q18: What is a media query?**
A media query applies CSS rules conditionally based on screen size or device type. Example: `@media (max-width: 768px)` applies its rules only when the screen is 768px wide or less — used for making mobile-responsive layouts.

---

**Q19: What is `z-index` and why is it used on the nav?**
`z-index` controls the stacking order of elements. A higher value means the element appears on top. The nav uses `z-index: 100` so it stays above the main content when it's sticky and the page scrolls under it.

---

**Q20: What is the purpose of `<meta name="viewport">`?**
It controls how the page scales on mobile devices. Without it, mobile browsers shrink the page to fit the screen. With `width=device-width, initial-scale=1.0`, the page uses the device's actual width and starts at normal zoom.

---

## 4. Frequently Asked Interview / Lab Questions

### HTML Questions

**Q: What are semantic HTML5 elements? Name five.**
Elements that have meaningful names describing their purpose.
Examples: `<header>`, `<nav>`, `<section>`, `<article>`, `<footer>`, `<aside>`, `<main>`.

**Q: What is the difference between `<b>` and `<strong>`?**
Both make text bold visually, but `<strong>` has semantic meaning (important text), while `<b>` is purely visual (bold with no importance implied).

**Q: What are HTML entities? Give two examples.**
Special codes representing characters that have meaning in HTML.
- `&amp;` → `&` (ampersand)
- `&#8377;` → `₹` (Indian Rupee)
- `&lt;` → `<` (less than)
- `&copy;` → `©` (copyright)

**Q: What is the `for` attribute in `<label>`?**
It links the label to an input field using the input's `id`. When the user clicks the label, the linked input gets focused.

**Q: What attributes does `<form>` have and what do they do?**
- `action` → URL where form data is sent. `#` means no server (placeholder).
- `method` → `get` (data in URL, visible) or `post` (data in body, secure).

**Q: What is the `required` attribute on form inputs?**
It's an HTML5 validation attribute. The browser prevents form submission if the field is empty, showing a built-in error message.

**Q: What is `href="#"` in an anchor tag?**
It's a placeholder link that goes nowhere (stays on the same page). Used when the URL is not yet set.

---

### CSS Questions

**Q: What is CSS specificity?**
When multiple CSS rules target the same element, specificity determines which one wins.
Specificity order (from lowest to highest):
1. Element selector (`p`, `div`) → specificity: 1
2. Class selector (`.class`) → specificity: 10
3. ID selector (`#id`) → specificity: 100
4. Inline style (`style=""`) → specificity: 1000

**Q: What does `flex: 1` mean?**
It tells the flex item to grow and take up an equal share of the available space in the flex container. If 3 items all have `flex: 1`, they each get 1/3 of the container width.

**Q: What is the difference between `margin` and `padding`?**
- `padding` — space INSIDE the element (between content and border).
- `margin` — space OUTSIDE the element (between the border and neighboring elements).

**Q: What does `opacity` do?**
Controls the transparency of an element. `opacity: 0` = fully invisible, `opacity: 1` = fully visible. `opacity: 0.95` is used on event images — they appear slightly transparent and become fully visible on hover.

**Q: What is `cursor: pointer`?**
Changes the mouse cursor to a hand pointer when hovering over an element. Used on buttons to indicate they are clickable.

**Q: How does `:nth-child(odd)` work?**
It selects elements that are in an odd position (1st, 3rd, 5th...) within their parent. Used on table rows to give alternating background colors (zebra striping).

---

## 5. Important HTML Tags Used

| Tag | What It Does | Example in Project |
|---|---|---|
| `<!DOCTYPE html>` | Declares HTML5 document type | First line of every HTML file |
| `<html lang="en">` | Root element; sets page language | Wraps all page content |
| `<head>` | Invisible metadata section | Contains meta, title, link |
| `<meta charset="UTF-8">` | Sets character encoding | In every HTML head |
| `<meta name="viewport">` | Makes page responsive | In every HTML head |
| `<title>` | Sets browser tab text | "TechNova 2026 - Home" |
| `<link rel="stylesheet">` | Links external CSS file | `href="css/style.css"` |
| `<body>` | All visible page content | Wraps header, nav, main, footer |
| `<header>` | Semantic top section | Logo, title, tagline |
| `<nav>` | Navigation menu | Links to all 7 pages |
| `<div>` | Generic block container | main-content, footer-content |
| `<section>` | Semantic content group | Each content block on a page |
| `<article>` | Self-contained content unit | Each event card |
| `<footer>` | Semantic bottom section | Organization info and links |
| `<h1>` to `<h4>` | Headings (h1=most important) | Page titles, section headings |
| `<p>` | Paragraph text | Descriptions, contact info |
| `<img>` | Displays an image | Logo, banner, event images, map |
| `<a href>` | Hyperlink | Navigation and footer links |
| `<span>` | Inline container | Badges, nav label, text labels |
| `<ul>` | Unordered (bullet) list | Objectives, rules |
| `<ol>` | Ordered (numbered) list | "Why Participate?" points |
| `<li>` | List item | Each bullet/numbered point |
| `<b>` | Bold text (no semantic meaning) | Field labels (Name, Email) |
| `<hr>` | Horizontal dividing line | Between sections on home page |
| `<br>` | Line break | Inside the address block |
| `<form>` | Form container | Registration form |
| `<label>` | Text label for input | "Full Name *", "Email *" |
| `<input>` | User input field | Text, email, tel, submit, reset |
| `<select>` | Dropdown menu | Year, event choice |
| `<option>` | Dropdown item | "1st Year", "Paper Presentation" |
| `<textarea>` | Multi-line text box | Address, comments |
| `<table>` | Table structure | Schedule and prize tables |
| `<thead>` | Table header section | Column labels (Time, Event, Venue) |
| `<tbody>` | Table data section | Schedule rows |
| `<tr>` | Table row | One row per event slot |
| `<th>` | Table header cell | "Time", "Event", "Venue" |
| `<td>` | Table data cell | "8:00 AM", "Inauguration" |

---

## 6. Important CSS Properties Used

| Property | What It Does | Example |
|---|---|---|
| `box-sizing: border-box` | Width includes padding + border | Applied to `*` (everything) |
| `margin` | Outer spacing (outside element) | `margin: 20px auto` (center) |
| `padding` | Inner spacing (inside element) | `padding: 20px 10px` |
| `font-family` | Sets the font | `Arial, sans-serif` |
| `font-size` | Text size | `16px`, `24px` |
| `font-weight` | Bold or normal | `bold`, `normal` |
| `color` | Text color | `#003366` (dark blue) |
| `background-color` | Element background | `#003366`, `#f5f5f5` |
| `text-align` | Text alignment | `center`, `left` |
| `text-transform` | Text case | `uppercase`, `capitalize` |
| `letter-spacing` | Space between characters | `2px` |
| `line-height` | Space between lines | `1.6` |
| `text-decoration` | Underline on links | `none` (removes underline) |
| `border` | Element border | `1px solid #cccccc` |
| `border-radius` | Rounded corners | `50%` (circle), `6px` |
| `border-left` | Left accent border | `4px solid #003366` |
| `border-collapse` | Table border merging | `collapse` (no double lines) |
| `display: flex` | Enable Flexbox | Nav, highlights, footer |
| `display: grid` | Enable CSS Grid | Event cards |
| `display: block` | Full-width block | Images, info-bar |
| `display: inline-block` | Inline with sizing | Badges, nav-label |
| `display: inline` | Flows with text | text-inline labels |
| `flex-wrap: wrap` | Items wrap to next row | Nav, highlights on small screen |
| `justify-content` | Horizontal Flex alignment | `center`, `space-between` |
| `align-items` | Vertical Flex alignment | `center` |
| `gap` | Space between flex/grid items | `20px` |
| `flex: 1` | Equal share of space | Cards, footer columns |
| `grid-template-columns` | Grid column definition | `repeat(2, 1fr)` |
| `position: sticky` | Stick to top on scroll | Nav bar |
| `top: 0` | Stick at top of viewport | With `position: sticky` |
| `z-index` | Stacking layer order | `100` on nav |
| `max-width` | Maximum element width | `960px`, `600px` |
| `width: 100%` | Full parent width | Images, form inputs |
| `height: auto` | Maintain image ratio | All responsive images |
| `opacity` | Transparency | `0.95` on event images |
| `cursor: pointer` | Hand cursor on hover | Buttons |
| `box-shadow` | Shadow around element | Cards, logo, images |
| `text-shadow` | Shadow behind text | Page headings |
| `transition` | Smooth animation | All hover effects |
| `transform: translateY` | Move element vertically | Highlight card lift on hover |
| `transform: scale()` | Resize element | Submit button on hover |
| `outline: none` | Remove focus outline | Form inputs (replaced with custom) |
| `min-width` | Minimum element width | `150px` on highlight cards |
| `content` | Insert content (pseudo-elements) | `"© "` in ::before |

---

## 7. Common Mistakes to Avoid

### HTML Mistakes

❌ **Using more than one `<h1>` per page**
Each page should have only ONE `<h1>` tag. Multiple h1s hurt SEO.

❌ **Forgetting `alt` on `<img>` tags**
Always include `alt="description"`. It's required for accessibility and SEO.

❌ **Using `<table>` for layout**
Tables are only for tabular data (rows and columns of data). Never use them to lay out page sections. Use Flexbox or Grid instead.

❌ **Using `<br>` for paragraph spacing**
Don't use `<br><br>` to add space between paragraphs. Use `<p>` tags properly with CSS `margin-bottom`.

❌ **Forgetting `</form>`, `</table>`, `</select>` closing tags**
HTML is forgiving with some unclosed tags, but form and table elements need proper closing tags to work correctly.

❌ **Not linking `<label>` to its input with `for` and `id`**
`<label for="email">` must match `<input id="email">`. Without this, clicking the label won't focus the input.

❌ **Writing `&` directly instead of `&amp;`**
In HTML, `&` has special meaning (HTML entities). Always write `&amp;` to display `&` in text.

❌ **Using inline CSS everywhere**
`style="..."` on every element makes code hard to maintain. Use class selectors in style.css and apply classes in HTML instead.

---

### CSS Mistakes

❌ **Forgetting `display: block` before `margin: auto` for centering**
`margin: 0 auto` only centers block-level elements. If the element is inline, centering won't work. Add `display: block` first.

❌ **Adding `position: sticky` without `top` value**
Without `top: 0`, sticky doesn't know WHERE to stick. Always add `top: 0` (or `top: 60px`, etc.).

❌ **Using `height: 100px` on images**
This distorts images if the width is different. Always use `height: auto` with a set `width` to maintain the aspect ratio.

❌ **Forgetting `border-collapse: collapse` on tables**
Without it, all table borders appear doubled (ugly double lines between cells).

❌ **Confusing `padding` and `margin`**
Padding = inside the border (background shows).
Margin = outside the border (transparent gap between elements).

❌ **Using `px` for font-size on everything**
`px` is fixed. If a user increases browser font size, `px` sizes don't respond. In real projects, prefer `rem` or `em`. For lab assignments, `px` is acceptable.

❌ **Not testing media queries**
Always test at different screen widths (use browser DevTools → Ctrl+Shift+M or F12). Media queries that are never tested often have bugs.

❌ **Writing media queries before the main styles**
Media queries should generally come AFTER the main styles in the CSS file, so they can properly override the default values.

---

## 8. One-Page Quick Revision Sheet

```
╔══════════════════════════════════════════════════════════════════╗
║           TECHNOVA 2026 — QUICK REVISION SHEET                  ║
╠══════════════════════════════════════════════════════════════════╣
║                                                                  ║
║  HTML STRUCTURE (every page):                                    ║
║  DOCTYPE → html → head (meta, title, link) → body               ║
║  body → header → nav → main-content div → footer                ║
║                                                                  ║
║  SEMANTIC TAGS:                                                  ║
║  <header>  = top section                                         ║
║  <nav>     = navigation links                                    ║
║  <section> = grouped content block                               ║
║  <article> = self-contained card (event cards)                   ║
║  <footer>  = bottom section                                      ║
║                                                                  ║
║  CSS SELECTOR TYPES:                                             ║
║  *          → Universal (all elements)                           ║
║  body, nav  → Element (tag name)                                 ║
║  .class     → Class (multiple elements)                          ║
║  #id        → ID (one unique element)                            ║
║  nav a      → Descendant (a inside nav)                          ║
║  input[type]→ Attribute selector                                 ║
║  :hover     → Pseudo-class (mouse over)                          ║
║  :focus     → Pseudo-class (clicked/tabbed)                      ║
║  :nth-child → Pseudo-class (by position)                         ║
║  ::before   → Pseudo-element (insert content)                    ║
║                                                                  ║
║  FLEXBOX (display: flex):                                        ║
║  Used for: nav, highlights, footer, contact cards                ║
║  Key props: flex-wrap, justify-content, align-items, gap, flex:1 ║
║                                                                  ║
║  CSS GRID (display: grid):                                       ║
║  Used for: event cards (technical + nontechnical pages)          ║
║  Key props: grid-template-columns: repeat(2, 1fr), gap           ║
║                                                                  ║
║  POSITIONING:                                                    ║
║  position: sticky; top: 0; z-index: 100; → nav bar              ║
║                                                                  ║
║  EFFECTS:                                                        ║
║  box-shadow: x y blur color     → shadow around elements         ║
║  text-shadow: x y blur color    → shadow behind text             ║
║  transition: property time      → smooth animation               ║
║  transform: translateY(-4px)    → lift card on hover             ║
║  transform: scale(1.03)         → grow button on hover           ║
║  opacity                        → transparency (0 to 1)          ║
║                                                                  ║
║  MEDIA QUERIES:                                                  ║
║  @media (max-width: 768px) → tablet                              ║
║  @media (max-width: 480px) → mobile                              ║
║  Changes: 1-column grid, vertical nav, stacked footer            ║
║                                                                  ║
║  TABLES:                                                         ║
║  table > thead > tr > th  (header cells)                         ║
║  table > tbody > tr > td  (data cells)                           ║
║  border-collapse: collapse → no double borders                   ║
║  :nth-child(odd/even)      → alternating row colors              ║
║                                                                  ║
║  FORMS:                                                          ║
║  <form action="#" method="post">                                 ║
║  input types: text, email, tel, submit, reset                    ║
║  <select> + <option>                                             ║
║  <textarea rows="4">                                             ║
║  <label for="id"> links to <input id="id">                       ║
║  :focus → blue border + glow on active input                     ║
║                                                                  ║
║  DISPLAY VALUES:                                                 ║
║  block        → full width, new line (.info-bar, images)         ║
║  inline       → no new line, no padding (.text-inline)           ║
║  inline-block → no new line, but accepts padding (.badge)        ║
╚══════════════════════════════════════════════════════════════════╝
```

---

## 9. 5-Minute Revision Before Lab Exam

> Read this 5 minutes before you walk into the lab exam or viva. It covers the most important points.

---

### ✅ The 7 Pages in This Project

| File | Purpose |
|---|---|
| index.html | Home — banner + highlights (Flexbox) |
| about.html | About — ul, ol, badge (inline-block) |
| technical.html | Technical Events — CSS Grid + tables |
| nontechnical.html | Non-Technical Events — CSS Grid + tables |
| schedule.html | Schedule — big HTML table |
| register.html | Registration — HTML Form (all input types) |
| contact.html | Contact — Flexbox contact cards + map image |

---

### ✅ Top 5 Concepts You Must Be Able to Explain

1. **Flexbox** → `display: flex` → arranges items in a row or column → used for nav, highlights, footer, contact cards.

2. **CSS Grid** → `display: grid` → rows AND columns → `grid-template-columns: repeat(2, 1fr)` → used for event cards.

3. **Sticky Nav** → `position: sticky; top: 0; z-index: 100;` → nav stays at top when scrolling.

4. **Media Queries** → `@media (max-width: 768px)` → makes layout responsive (changes to 1 column on mobile).

5. **Pseudo-elements** → `::before { content: "© "; }` → adds content without editing HTML.

---

### ✅ 5 Things to Say Confidently in Viva

- **"The project uses HTML5 semantic elements like `<header>`, `<nav>`, `<section>`, `<article>`, and `<footer>` for better accessibility and SEO."**

- **"The navigation bar uses `position: sticky` so it stays visible at the top while scrolling."**

- **"The event cards use CSS Grid with `grid-template-columns: repeat(2, 1fr)` to create a 2-column layout."**

- **"The registration form uses different input types: text, email, tel, select, and textarea — each appropriate for the type of data collected."**

- **"Media queries at 768px and 480px make the layout responsive — the grid switches to 1 column and the nav stacks vertically on small screens."**

---

### ✅ Key Shorthand You Must Know

```css
/* Margin shorthand: top right bottom left */
margin: 20px auto;         /* 20px top/bottom, auto left/right (centering) */
margin: 10px 15px;         /* 10px top/bottom, 15px left/right */
margin: 10px 15px 5px 20px; /* top right bottom left */

/* Padding shorthand (same order) */
padding: 20px 10px;        /* 20px top/bottom, 10px left/right */

/* Border shorthand */
border: 1px solid #cccccc; /* width style color */

/* Box Shadow shorthand */
box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2); /* x y blur color */

/* Transition shorthand */
transition: background-color 0.3s, color 0.3s; /* property duration */
```

---

### ✅ Must-Know Difference: Flexbox vs Grid

| | Flexbox | Grid |
|---|---|---|
| Dimensions | 1D (row OR column) | 2D (rows AND columns) |
| Best for | Navbars, card rows, footers | Event card layouts (2×2) |
| Key property | `display: flex` | `display: grid` |
| Layout control | One axis at a time | Both axes together |

---

### ✅ Final Mental Checklist Before You Start Coding in Lab

- [ ] File structure: `index.html`, `css/style.css`, `assets/images/`
- [ ] Every HTML page starts with `<!DOCTYPE html>` and `<html lang="en">`
- [ ] `<meta charset="UTF-8">` and `<meta name="viewport">` in every `<head>`
- [ ] Link CSS with `<link rel="stylesheet" href="css/style.css">`
- [ ] Only ONE `<h1>` per page
- [ ] Every `<img>` has an `alt` attribute
- [ ] Form has `action` and `method` attributes
- [ ] `<label for="">` matches `<input id="">`
- [ ] Tables have `<thead>` and `<tbody>`
- [ ] CSS uses `box-sizing: border-box` on `*`

---

*Good luck with your lab exam and viva! You've got this! 🚀*
