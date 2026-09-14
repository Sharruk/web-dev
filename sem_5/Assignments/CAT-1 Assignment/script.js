// ===== Memory Card Matching Game =====

// 8 symbols, each used twice = 16 cards
const symbols = ["🍎", "🍌", "🍇", "🍉", "🍒", "🍋", "🍓", "🥝"];

// Game state variables
let cards = [];
let flippedCards = [];
let matchedCards = [];
let lockBoard = false;
let moveCount = 0;

// DOM elements
const gameBoard = document.getElementById("game-board");
const statusMessage = document.getElementById("status-message");
const movesDisplay = document.getElementById("moves");
const restartBtn = document.getElementById("restart-btn");

// ===== Shuffle cards using Fisher-Yates algorithm =====
function shuffleCards(array) {
  for (let i = array.length - 1; i > 0; i--) {
    const j = Math.floor(Math.random() * (i + 1));
    [array[i], array[j]] = [array[j], array[i]];
  }
  return array;
}

// ===== Create the game board =====
function createBoard() {
  // Reset state
  gameBoard.innerHTML = "";
  flippedCards = [];
  matchedCards = [];
  lockBoard = false;
  moveCount = 0;
  movesDisplay.textContent = "Moves: 0";
  statusMessage.textContent = "";

  // Duplicate symbols to form pairs, then shuffle
  cards = shuffleCards([...symbols, ...symbols]);

  // Create a card element for each symbol
  cards.forEach((symbol, index) => {
    const card = document.createElement("button");
    card.classList.add("card");
    card.setAttribute("data-symbol", symbol);
    card.setAttribute("data-index", index);
    card.setAttribute("aria-label", "Memory card, face down");

    const symbolSpan = document.createElement("span");
    symbolSpan.classList.add("card-symbol");
    symbolSpan.textContent = symbol;
    card.appendChild(symbolSpan);

    card.addEventListener("click", () => flipCard(card));
    gameBoard.appendChild(card);
  });
}

// ===== Flip a card =====
function flipCard(card) {
  // Ignore click if board is locked, or card is already flipped/matched
  if (lockBoard) return;
  if (card.classList.contains("flipped") || card.classList.contains("matched")) return;
  if (flippedCards.length === 2) return;

  card.classList.add("flipped");
  card.setAttribute("aria-label", "Memory card showing " + card.dataset.symbol);
  flippedCards.push(card);

  if (flippedCards.length === 2) {
    moveCount++;
    movesDisplay.textContent = "Moves: " + moveCount;
    checkMatch();
  }
}

// ===== Check if the two flipped cards match =====
function checkMatch() {
  const [firstCard, secondCard] = flippedCards;
  const isMatch = firstCard.dataset.symbol === secondCard.dataset.symbol;

  if (isMatch) {
    handleMatch(firstCard, secondCard);
  } else {
    lockBoard = true; // Prevent selecting a third card while checking
    setTimeout(() => {
      firstCard.classList.remove("flipped");
      secondCard.classList.remove("flipped");
      firstCard.setAttribute("aria-label", "Memory card, face down");
      secondCard.setAttribute("aria-label", "Memory card, face down");
      flippedCards = [];
      lockBoard = false;
    }, 1000); // 1 second delay before flipping back
  }
}

// ===== Handle a successful match =====
function handleMatch(firstCard, secondCard) {
  firstCard.classList.add("matched");
  secondCard.classList.add("matched");
  firstCard.setAttribute("aria-label", "Matched card: " + firstCard.dataset.symbol);
  secondCard.setAttribute("aria-label", "Matched card: " + secondCard.dataset.symbol);

  matchedCards.push(firstCard, secondCard);
  flippedCards = [];

  // Check if all pairs have been matched
  if (matchedCards.length === cards.length) {
    statusMessage.textContent = "Congratulations! You matched all pairs in " + moveCount + " moves!";
  }
}

// ===== Restart the game =====
function resetGame() {
  createBoard();
}

// ===== Event Listeners =====
restartBtn.addEventListener("click", resetGame);

// ===== Start the game on page load =====
createBoard();
