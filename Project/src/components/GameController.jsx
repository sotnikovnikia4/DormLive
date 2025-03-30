import { useEffect, useRef, useState } from 'react';
import { Button } from '@/components/ui/button';
import { toast } from '@/components/ui/use-toast';
import { gameScores } from '@/data/mockData';

const GAME_DURATION = 30; // seconds

const GameController = () => {
  const [isPlaying, setIsPlaying] = useState(false);
  const [gameOver, setGameOver] = useState(false);
  const [score, setScore] = useState(0);
  const [timeLeft, setTimeLeft] = useState(GAME_DURATION);
  const [playerPosition, setPlayerPosition] = useState({ x: 50, y: 50 });
  const [superintendentPosition, setSuperintendentPosition] = useState({ x: 10, y: 10 });
  const gameAreaRef = useRef(null);
  const [highScores, setHighScores] = useState(gameScores);
  const requestRef = useRef();
  const playerSpeed = 5;
  const superintendentSpeed = 3;

  useEffect(() => {
    if (isPlaying && !gameOver) {
      const timer = setInterval(() => {
        setTimeLeft((prev) => {
          if (prev <= 1) {
            setIsPlaying(false);
            setGameOver(true);
            clearInterval(timer);
            return 0;
          }
          return prev - 1;
        });
      }, 1000);

      return () => clearInterval(timer);
    }
  }, [isPlaying, gameOver]);

  useEffect(() => {
    const handleKeyDown = (e) => {
      if (!isPlaying || gameOver) return;

      const gameArea = gameAreaRef.current;
      if (!gameArea) return;

      const rect = gameArea.getBoundingClientRect();
      const maxX = rect.width - 30; // player width
      const maxY = rect.height - 30; // player height

      switch (e.key) {
        case 'ArrowUp':
          setPlayerPosition((prev) => ({
            ...prev,
            y: Math.max(0, prev.y - playerSpeed),
          }));
          break;
        case 'ArrowDown':
          setPlayerPosition((prev) => ({
            ...prev,
            y: Math.min(maxY, prev.y + playerSpeed),
          }));
          break;
        case 'ArrowLeft':
          setPlayerPosition((prev) => ({
            ...prev,
            x: Math.max(0, prev.x - playerSpeed),
          }));
          break;
        case 'ArrowRight':
          setPlayerPosition((prev) => ({
            ...prev,
            x: Math.min(maxX, prev.x + playerSpeed),
          }));
          break;
      }
    };

    window.addEventListener('keydown', handleKeyDown);
    return () => window.removeEventListener('keydown', handleKeyDown);
  }, [isPlaying, gameOver]);

  const updateGameState = () => {
    if (!isPlaying || gameOver) return;

    const dx = playerPosition.x - superintendentPosition.x;
    const dy = playerPosition.y - superintendentPosition.y;
    const distance = Math.sqrt(dx * dx + dy * dy);

    if (distance < 25) { // Collision detection
      setIsPlaying(false);
      setGameOver(true);
      return;
    }

    if (distance > 0) {
      const vx = (dx / distance) * superintendentSpeed;
      const vy = (dy / distance) * superintendentSpeed;

      setSuperintendentPosition((prev) => ({
        x: prev.x + vx,
        y: prev.y + vy,
      }));
    }

    setScore((prev) => prev + 1);

    requestRef.current = requestAnimationFrame(updateGameState);
  };

  useEffect(() => {
    if (isPlaying && !gameOver) {
      requestRef.current = requestAnimationFrame(updateGameState);
    } else if (gameOver) {
      if (requestRef.current) {
        cancelAnimationFrame(requestRef.current);
      }
    }

    return () => {
      if (requestRef.current) {
        cancelAnimationFrame(requestRef.current);
      }
    };
  }, [isPlaying, gameOver, playerPosition, superintendentPosition]);

  const startGame = () => {
    setIsPlaying(true);
    setGameOver(false);
    setScore(0);
    setTimeLeft(GAME_DURATION);
    setPlayerPosition({ x: 50, y: 50 });
    setSuperintendentPosition({ x: 10, y: 10 });
  };

  const saveScore = () => {
    const newScore = {
      id: `score${Date.now()}`,
      userId: '12345',
      userName: 'Иван Иванов',
      score,
      date: new Date().toISOString().split('T')[0],
    };

    const newHighScores = [...highScores, newScore]
      .sort((a, b) => b.score - a.score)
      .slice(0, 5);

    setHighScores(newHighScores);

    toast({
      title: 'Результат сохранен!',
      description: `Вы набрали ${score} очков!`,
    });
  };

  return (
    <div className="w-full max-w-2xl mx-auto">
      <div className="mb-4 text-center">
        <h2 className="text-2xl font-bold">Убеги от коменданта</h2>
        <p className="text-gray-500">Используйте стрелки на клавиатуре для движения</p>
      </div>

      <div className="flex justify-between mb-4">
        <div className="text-lg font-bold">Счет: {score}</div>
        <div className={`text-lg font-bold ${timeLeft < 10 ? 'text-red-500' : ''}`}>
          Время: {timeLeft}с
        </div>
      </div>

      <div
        ref={gameAreaRef}
        className="relative w-full h-64 border-2 border-primary rounded-lg overflow-hidden bg-secondary mb-4"
      >
        {!isPlaying && !gameOver && (
          <div className="absolute inset-0 flex items-center justify-center bg-black bg-opacity-50 text-white">
            <Button onClick={startGame} variant="default" size="lg">
              Начать игру
            </Button>
          </div>
        )}

        {gameOver && (
          <div className="absolute inset-0 flex flex-col items-center justify-center bg-black bg-opacity-50 text-white">
            <h3 className="text-xl mb-2">Игра окончена!</h3>
            <p className="mb-4">Счет: {score}</p>
            <div className="flex space-x-4">
              <Button onClick={saveScore} variant="secondary">
                Сохранить результат
              </Button>
              <Button onClick={startGame} variant="default">
                Играть снова
              </Button>
            </div>
          </div>
        )}

        {/* Player character */}
        <div
          className="absolute w-8 h-8 bg-blue-500 rounded-full flex items-center justify-center text-white"
          style={{
            left: `${playerPosition.x}px`,
            top: `${playerPosition.y}px`,
            transition: 'left 0.1s, top 0.1s',
          }}
        >
          😰
        </div>

        {/* Superintendent character */}
        <div
          className="absolute w-8 h-8 bg-red-500 rounded-full flex items-center justify-center text-white"
          style={{
            left: `${superintendentPosition.x}px`,
            top: `${superintendentPosition.y}px`,
            transition: 'left 0.1s, top 0.1s',
          }}
        >
          👮
        </div>
      </div>

      {/* High Scores */}
      <div className="mt-6">
        <h3 className="text-xl font-bold mb-2">Таблица лидеров</h3>
        <div className="bg-secondary rounded-lg p-4">
          {highScores.map((entry, index) => (
            <div key={entry.id} className="flex justify-between items-center py-1 border-b last:border-0">
              <div className="flex items-center">
                <span className="font-medium mr-2">#{index + 1}</span>
                <span>{entry.userName}</span>
              </div>
              <span className="font-bold">{entry.score}</span>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};

export default GameController;