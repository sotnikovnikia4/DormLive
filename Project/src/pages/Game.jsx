import Navbar from '@/components/Navbar';
import { Button } from '@/components/ui/button';
import { Link } from 'react-router-dom';
import { ArrowLeft } from 'lucide-react';
import GameController from '@/components/GameController';

const Game = () => {
  return (
    <div className="min-h-screen bg-gray-50 pb-20">
      <Navbar />
      <main className="container mx-auto pt-20 px-4">
        <div className="flex items-center space-x-4 mb-6">
          <Link to="/">
            <Button variant="ghost" size="icon">
              <ArrowLeft className="h-5 w-5" />
            </Button>
          </Link>
          <h1 className="text-2xl font-bold">Мини-игра</h1>
        </div>

        <div className="bg-white rounded-lg shadow-sm p-6">
          <GameController />
        </div>
      </main>
    </div>
  );
};

export default Game;