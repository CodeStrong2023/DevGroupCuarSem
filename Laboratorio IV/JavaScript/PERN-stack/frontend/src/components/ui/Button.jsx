export function Button({ children }) {
  return (
    <button className="relative inline-flex items-center bg-blue-500 gap-1.5 rounded-md px-3 py-1.5 text-sm font-semibold text-white shadow-sm hover:bg-blue-400 focus-visible:outline focus-visible:outline2 focus-visible:outline-offset-2 focus-visible:outline-blue-500 disabled:opacity-50 disabled:cursor-not-allowed">
      {children}
    </button>
  );
}

export default Button;
