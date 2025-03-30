import Navbar from '@/components/Navbar';
import { Tabs, TabsContent, TabsList, TabsTrigger } from '@/components/ui/tabs';
import { currentUser, announcements, topUsers } from '@/data/mockData';
import AnnouncementCard from '@/components/AnnouncementCard';
import UserStatsCard from '@/components/UserStatsCard';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Textarea } from '@/components/ui/textarea';
import { Dialog, DialogContent, DialogDescription, DialogFooter, DialogHeader, DialogTitle, DialogTrigger } from '@/components/ui/dialog';
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from '@/components/ui/select';
import { useState } from 'react';
import { Plus } from 'lucide-react';
import { useToast } from '@/components/ui/use-toast';
import { Link } from 'react-router-dom';

const Index = () => {
  const { toast } = useToast();
  const [filter, setFilter] = useState('all');
  const [dialogOpen, setDialogOpen] = useState(false);

  const filteredAnnouncements = announcements.filter(
    (announcement) => filter === 'all' || announcement.type === filter
  );

  const handleCreateAnnouncement = (event) => {
    event.preventDefault();
    setDialogOpen(false);
    toast({
      title: 'Объявление создано!',
      description: 'Ваше объявление успешно добавлено на доску',
    });
  };

  return (
    <div className="min-h-screen bg-gray-50 pb-20">
      <Navbar />
      <main className="container mx-auto pt-20 px-4">
        <div className="flex justify-between items-center mb-6">
          <h1 className="text-2xl font-bold">Общага Live</h1>
          <div className="flex items-center space-x-2">
            <div className="text-sm font-medium flex items-center">
              <span className="mr-1">ОбщагаCoin:</span>
              <span className="text-primary font-bold">{currentUser.dormCoin}</span>
            </div>
            <Button variant="default" asChild>
              <Link to="/game">Мини-игра</Link>
            </Button>
          </div>
        </div>

        <div className="grid grid-cols-1 md:grid-cols-3 gap-6 mb-6">
          <div className="md:col-span-2">
            <div className="flex justify-between items-center mb-4">
              <h2 className="text-xl font-semibold">Доска объявлений</h2>
              <Dialog open={dialogOpen} onOpenChange={setDialogOpen}>
                <DialogTrigger asChild>
                  <Button size="sm">
                    <Plus className="mr-1 h-4 w-4" />
                    Создать
                  </Button>
                </DialogTrigger>
                <DialogContent>
                  <form onSubmit={handleCreateAnnouncement}>
                    <DialogHeader>
                      <DialogTitle>Новое объявление</DialogTitle>
                      <DialogDescription>
                        Создайте объявление для доски объявлений общежития
                      </DialogDescription>
                    </DialogHeader>
                    <div className="space-y-4 py-4">
                      <div className="space-y-2">
                        <label className="text-sm font-medium">Заголовок</label>
                        <Input placeholder="Введите заголовок" required />
                      </div>
                      <div className="space-y-2">
                        <label className="text-sm font-medium">Описание</label>
                        <Textarea placeholder="Введите подробное описание" required />
                      </div>
                      <div className="space-y-2">
                        <label className="text-sm font-medium">Тип объявления</label>
                        <Select defaultValue="sell">
                          <SelectTrigger>
                            <SelectValue placeholder="Выберите тип" />
                          </SelectTrigger>
                          <SelectContent>
                            <SelectItem value="sell">Продажа</SelectItem>
                            <SelectItem value="search">Поиск</SelectItem>
                            <SelectItem value="event">Событие</SelectItem>
                          </SelectContent>
                        </Select>
                      </div>
                      <div className="space-y-2">
                        <label className="text-sm font-medium">Местоположение</label>
                        <Input placeholder="Например: 3 этаж, комната 301" />
                      </div>
                      <div className="space-y-2">
                        <label className="text-sm font-medium">Цена (если продажа)</label>
                        <Input type="number" placeholder="В рублях" />
                      </div>
                    </div>
                    <DialogFooter>
                      <Button type="submit">Опубликовать</Button>
                    </DialogFooter>
                  </form>
                </DialogContent>
              </Dialog>
            </div>

            <Tabs defaultValue="all" className="space-y-4">
              <TabsList>
                <TabsTrigger value="all" onClick={() => setFilter('all')}>
                  Все
                </TabsTrigger>
                <TabsTrigger value="sell" onClick={() => setFilter('sell')}>
                  Продажа
                </TabsTrigger>
                <TabsTrigger value="search" onClick={() => setFilter('search')}>
                  Поиск
                </TabsTrigger>
                <TabsTrigger value="event" onClick={() => setFilter('event')}>
                  События
                </TabsTrigger>
              </TabsList>

              <div className="space-y-4">
                {filteredAnnouncements.map((announcement) => (
                  <AnnouncementCard key={announcement.id} announcement={announcement} />
                ))}
              </div>
            </Tabs>
          </div>

          <div>
            <h2 className="text-xl font-semibold mb-4">Топ студентов</h2>
            <div className="space-y-4">
              {topUsers.map((user, index) => (
                <UserStatsCard key={user.id} user={user} rank={index + 1} />
              ))}
            </div>
            
            <div className="mt-6">
              <h2 className="text-xl font-semibold mb-4">Быстрые действия</h2>
              <div className="grid grid-cols-2 gap-2">
                <Button variant="outline" className="w-full" asChild>
                  <Link to="/chat">
                    Чаты
                  </Link>
                </Button>
                <Button variant="outline" className="w-full" asChild>
                  <Link to="/profile">
                    Профиль
                  </Link>
                </Button>
                <Button variant="outline" className="w-full" asChild>
                  <Link to="/game">
                    Мини-игра
                  </Link>
                </Button>
                <Button variant="outline" className="w-full">
                  Рейтинг
                </Button>
              </div>
            </div>
          </div>
        </div>
      </main>
    </div>
  );
};

export default Index;