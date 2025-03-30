import { toast as sonnerToast } from 'sonner';

// Хук useToast для удобного использования
const useToast = () => {
  const showToast = (options) => {
    sonnerToast(options);
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