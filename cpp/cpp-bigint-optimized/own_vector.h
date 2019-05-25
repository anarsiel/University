#ifndef BIGINT_OWN_VECTOR_H
#define BIGINT_OWN_VECTOR_H

#include <memory>

struct own_vector {

    own_vector();
    ~own_vector();

    explicit own_vector(size_t n);

    own_vector(own_vector const &other) noexcept;

    inline size_t size() const {return sz;}

    uint32_t &operator[](size_t i);
    uint32_t const &operator[](size_t i) const;

    void push_back(uint32_t val);
    void pop_back();
    uint32_t &back();

    void swap(own_vector &other);

    friend bool operator==(own_vector const &a, own_vector const &b);
    own_vector &operator=(own_vector other) noexcept;

    uint32_t *begin() const noexcept;

    void make_copy();

private:
    size_t sz;
    static const size_t LITTLE_ARRAY_SZ = 2;

    struct big_number {
        size_t capacity;
        std::shared_ptr<uint32_t> sharedPtr;

        big_number() : capacity(0), sharedPtr(nullptr) {};

        void ensure_capacity(size_t size);

        big_number(size_t size, uint32_t *array);
    };

    bool is_big;
    union combined_data {
        big_number big_data;
        uint32_t small_data[LITTLE_ARRAY_SZ];

        ~combined_data() {};

    } union_data{};

    void swap_big_small(combined_data & b);
};

#endif //BIGINT_OWN_VECTOR_H