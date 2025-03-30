
import Navbar from "@/components/Navbar";
import { Button } from "@/components/ui/button";
import { currentUser, gameScores } from "@/data/mockData";
import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Badge } from "@/components/ui/badge";
import { Coins, Trophy, Home, MapPin, ArrowLeft } from "lucide-react";
import { Link } from "react-router-dom";
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs";
import { Progress } from "@/components/ui/progress";

const Profile = () => {
  const nextLevel = {
    level: 2,
    coinsNeeded: 200,
    progress: (currentUser.dormCoin / 200) * 100
  };

  const userGameScore = gameScores.find(score => score.userId === currentUser.id);

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
          <h1 className="text-2xl font-bold">Мой профиль</h1>
        </div>

        <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
          <div className="md:col-span-1">
            <Card>
              <CardHeader className="text-center">
                <Avatar className="w-24 h-24 mx-auto">
                  <AvatarImage src={currentUser.avatar} alt={currentUser.name} />
                  <AvatarFallback>{currentUser.name.substring(0, 2)}</AvatarFallback>
                </Avatar>
                <CardTitle className="mt-2">{currentUser.name}</CardTitle>
              </CardHeader>
              <CardContent className="space-y-4">
                <div className="flex items-center gap-2">
                  <Coins className="h-5 w-5 text-dormcoin" />
                  <div className="flex-1">
                    <div className="flex justify-between items-center">
                      <span className="text-sm text-gray-500">ОбщагаCoin</span>
                      <span className="font-bold">{currentUser.dormCoin}</span>
                    </div>
                    <Progress value={nextLevel.progress} className="h-2 mt-1" />
                    <div className="flex justify-between items-center text-xs mt-1">
                      <span>Уровень 1</span>
                      <span>Уровень {nextLevel.level}: {nextLevel.coinsNeeded - currentUser.dormCoin} монет</span>
                    </div>
                  </div>
                </div>

                {userGameScore && (
                  <div className="flex items-center gap-2">
                    <Trophy className="h-5 w-5 text-primary" />
                    <div>
                      <span className="text-sm text-gray-500 block">Лучший счет в игре</span>
                      <span className="font-bold">{userGameScore.score}</span>
                    </div>
                  </div>
                )}

                <div className="flex items-center gap-2">
                  <Home className="h-5 w-5 text-gray-500" />
                  <div>
                    <span className="text-sm text-gray-500 block">Комната</span>
                    <span className="font-bold">{currentUser.roomNumber}</span>
                  </div>
                </div>

                <div className="flex items-center gap-2">
                  <MapPin className="h-5 w-5 text-gray-500" />
                  <div>
                    <span className="text-sm text-gray-500 block">Этаж</span>
                    <span className="font-bold">{currentUser.floor}</span>
                  </div>
                </div>

                <div className="pt-2">
                  <h3 className="text-sm font-medium mb-2">Достижения</h3>
                  <div className="flex flex-wrap gap-1">
                    {currentUser.badges.map((badge) => (
                      <Badge 
                        key={badge.id} 
                        variant="secondary"
                        className="flex items-center gap-1 py-1"
                        title={badge.description}
                      >
                        <span>{badge.icon}</span>
                        <span>{badge.name}</span>
                      </Badge>
                    ))}
                  </div>
                </div>
              </CardContent>
            </Card>
          </div>

          <div className="md:col-span-2">
            <Tabs defaultValue="activity">
              <TabsList className="grid grid-cols-3 mb-4">
                <TabsTrigger value="activity">Активность</TabsTrigger>
                <TabsTrigger value="achievements">Достижения</TabsTrigger>
                <TabsTrigger value="settings">Настройки</TabsTrigger>
              </TabsList>
              
              <TabsContent value="activity" className="space-y-4">
                <Card>
                  <CardHeader>
                    <CardTitle className="text-lg">Недавняя активность</CardTitle>
                  </CardHeader>
                  <CardContent>
                    <div className="space-y-4">
                      <div className="p-4 bg-secondary rounded-lg">
                        <div className="flex justify-between items-start">
                          <div>
                            <h3 className="font-medium">Сыграл в "Убеги от коменданта"</h3>
                            <p className="text-sm text-gray-500">Счет: {userGameScore?.score || 0}</p>
                          </div>
                          <Badge variant="outline">+10 монет</Badge>
                        </div>
                      </div>
                      
                      <div className="p-4 bg-secondary rounded-lg">
                        <div className="flex justify-between items-start">
                          <div>
                            <h3 className="font-medium">Получил лайки в чате "Кулинария"</h3>
                            <p className="text-sm text-gray-500">5 лайков за сообщение</p>
                          </div>
                          <Badge variant="outline">+5 монет</Badge>
                        </div>
                      </div>
                      
                      <div className="p-4 bg-secondary rounded-lg">
                        <div className="flex justify-between items-start">
                          <div>
                            <h3 className="font-medium">Присоединился к общежитию</h3>
                            <p className="text-sm text-gray-500">Добро пожаловать!</p>
                          </div>
                          <Badge variant="outline">+50 монет</Badge>
                        </div>
                      </div>
                    </div>
                  </CardContent>
                </Card>
              </TabsContent>
              
              <TabsContent value="achievements" className="space-y-4">
                <Card>
                  <CardHeader>
                    <CardTitle className="text-lg">Доступные достижения</CardTitle>
                  </CardHeader>
                  <CardContent>
                    <div className="space-y-4">
                      <div className="p-4 bg-secondary rounded-lg">
                        <div className="flex items-start gap-4">
                          <div className="text-3xl">🌟</div>
                          <div className="flex-1">
                            <h3 className="font-medium">Легенда общаги</h3>
                            <p className="text-sm text-gray-500">Получите 100+ лайков в чатах</p>
                            <Progress value={5} className="h-2 mt-2" />
                            <p className="text-xs text-gray-500 mt-1">5/100 лайков</p>
                          </div>
                        </div>
                      </div>
                      
                      <div className="p-4 bg-secondary rounded-lg">
                        <div className="flex items-start gap-4">
                          <div className="text-3xl">🎯</div>
                          <div className="flex-1">
                            <h3 className="font-medium">Организатор</h3>
                            <p className="text-sm text-gray-500">Создайте 10 объявлений о мероприятиях</p>
                            <Progress value={0} className="h-2 mt-2" />
                            <p className="text-xs text-gray-500 mt-1">0/10 мероприятий</p>
                          </div>
                        </div>
                      </div>
                      
                      <div className="p-4 bg-secondary rounded-lg">
                        <div className="flex items-start gap-4">
                          <div className="text-3xl">👨‍🍳</div>
                          <div className="flex-1">
                            <h3 className="font-medium">Шеф-повар</h3>
                            <p className="text-sm text-gray-500">Поделитесь 10 рецептами в чате "Кулинария"</p>
                            <Progress value={10} className="h-2 mt-2" />
                            <p className="text-xs text-gray-500 mt-1">1/10 рецептов</p>
                          </div>
                        </div>
                      </div>
                    </div>
                  </CardContent>
                </Card>
              </TabsContent>
              
              <TabsContent value="settings" className="space-y-4">
                <Card>
                  <CardHeader>
                    <CardTitle className="text-lg">Настройки профиля</CardTitle>
                  </CardHeader>
                  <CardContent>
                    <form className="space-y-4">
                      <div className="space-y-2">
                        <label className="text-sm font-medium">Имя</label>
                        <Input placeholder="Ваше имя" defaultValue={currentUser.name} />
                      </div>
                      <div className="space-y-2">
                        <label className="text-sm font-medium">Комната</label>
                        <Input placeholder="Номер комнаты" defaultValue={currentUser.roomNumber} />
                      </div>
                      <div className="space-y-2">
                        <label className="text-sm font-medium">Этаж</label>
                        <Input type="number" placeholder="Этаж" defaultValue={currentUser.floor?.toString()} />
                      </div>
                      <Button>Сохранить изменения</Button>
                    </form>
                  </CardContent>
                </Card>
              </TabsContent>
            </Tabs>
          </div>
        </div>
      </main>
    </div>
  );
};

import { Input } from "@/components/ui/input";

export default Profile;
