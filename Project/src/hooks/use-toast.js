import { toast as sonnerToast } from 'sonner';

// Константы для ограничения тостов и их удаления
const TOAST_LIMIT = 1;
const TOAST_REMOVE_DELAY = 10000; // 10 секунд

// Генерация уникального ID для тостов
let count = 0;
function genId() {
  count = (count + 1) % Number.MAX_SAFE_INTEGER;
  return count.toString();
}

// Очередь для удаления тостов
const toastTimeouts = new Map();

const addToRemoveQueue = (toastId) => {
  if (toastTimeouts.has(toastId)) {
    return;
  }

  const timeout = setTimeout(() => {
    toastTimeouts.delete(toastId);
    sonnerToast.dismiss(toastId); // Удаляем тост через sonner
  }, TOAST_REMOVE_DELAY);

  toastTimeouts.set(toastId, timeout);
};

// Хук useToast для удобного использования
const useToast = () => {
  const showToast = (options) => {
    const id = genId();
    sonnerToast(options);

    // Добавляем тост в очередь на удаление
    addToRemoveQueue(id);

    return {
      id,
      dismiss: () => sonnerToast.dismiss(id), // Метод для ручного удаления тоста
    };
  };

  const hideToast = (id) => {
    sonnerToast.dismiss(id); // Используем метод dismiss из sonner
  };

  return {
    toast: showToast,
    dismiss: hideToast,
  };
};

// Экспортируем функцию toast напрямую
const customToast = (options) => {
  sonnerToast(options);
};

export { useToast, customToast as toast };